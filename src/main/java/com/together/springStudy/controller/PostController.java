package com.together.springStudy.controller;

import com.together.springStudy.model.*;
import com.together.springStudy.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/together/post")
public class PostController {

//    서비스와 여기서 private final을 해주지 않으면
//    Bean주입이 안된다고함. -> NullPointExection 뜸 bean 공부해보기..

//    서비스 객체는 final 안해줘도 되긴하네?
    @Autowired
    PostService postService;

//    post 관련
    @Operation(summary = "모든 게시물을 가져오는 메인 sns화면",
            description = "모든 게시물을 가져오는 메인 sns화면으로 userId 값을 넘길 경우 해당 유저 게시물 선택됨.")
    @PostMapping("/getAllPostForMain")
    public SnsMainPageData getAllPostForMain(@RequestBody UserId userId){
        log.debug("getAllPostForMain 메인 페이지 통신 실행.");
        SnsMainPageData snsMainPageData = new SnsMainPageData();
        snsMainPageData.setSnsMainPosts(postService.getAllPostForMain(userId.getUserId()));
        snsMainPageData.setLastNoticeDate(postService.getLastNoticeDate().getPostCreationDate());
        log.debug("snsMainPageData : {}", snsMainPageData);

        return snsMainPageData;
//        postMainDataList가 null일 경우 등에 대한 예외처리 해야할 듯?
    }

    @PostMapping("/getPostByPrimaryKey")
    public PostMainData getPostByPrimaryKey(@RequestBody PostId postId){
        log.debug("파라미터 : {}", postId);
        PostMainData postMainData = postService.getPostByPrimaryKey(postId);
        log.debug("postData : {}", postMainData);
        return postMainData;
    }

    @PostMapping("/getAllNotice")
    public List<PostMainData> getAllNotice(UserId userId){
        BoardId boardId = new BoardId();
        boardId.setBoardId(1);
        boardId.setUserId(userId.getUserId());
        List<PostMainData> postMainDataList = postService.getPostByBoardId(boardId);
        log.debug("postMainDataList : {}", postMainDataList);
        return postMainDataList;
    }

    @PostMapping("/getPostForKeyword")
    public List<PostMainData> getPostForKeyword(@RequestBody Keyword keyword){
        log.debug("keyword : {}", keyword);
        List<PostMainData> postMainDataList = postService.getPostForKeyword(keyword);
        log.debug("keyword post : {}", postMainDataList);
        return postMainDataList;

    }

    @PostMapping("/getPostsByUserId")
    public List<PostMainData> getPostsByUserId(@RequestBody UserId userId){
        log.debug("{}", userId);
        List<PostMainData> postMainDataList = postService.getPostsByUserId(userId.getUserId());
        log.debug("가져온 postData : {}", postMainDataList);
        return postMainDataList;
    }

    @PostMapping("/getPostsByClubId")
    public List<PostMainData> getPostsByClubId(@RequestBody ClubId clubId){
        log.debug("동아리 pk로 게시물 조회 : {}", clubId);
        BoardId boardId = postService.getBoardIdByClubId(clubId.getClubId());
        boardId.setUserId(clubId.getUserId());
        log.debug("{}", boardId);
//        if(boardId != null)
        List<PostMainData> postMainDataList = postService.getPostByBoardId(boardId);
        log.debug("{}", postMainDataList);
        return postMainDataList;
    }

    @PostMapping("/getPostByBoardId")
    public List<PostMainData> getPostByBoardId(@RequestBody BoardId boardId){
        log.debug("getPostsByBoardId 게시판 id로 게시물 불러오기 : {}", boardId);
        List<PostMainData> postMainDataList = postService.getPostByBoardId(boardId);
        return postMainDataList;
    }

    @PostMapping("/getClubPostCreatedByUser")
    public List<PostMainData> getClubPostCreatedByUser(@RequestBody UserId userId){
        log.debug("유저가 작성한 클럽 게시물 불러오기 : {}", userId);
        List<PostMainData> postMainDataList = postService.getClubPostCreatedByUser(userId);
        return postMainDataList;
    }

    @PostMapping("/getImageByPrimaryKey")
    public TestImage getImageByPrimaryKey(@RequestParam int idx){
        TestImage testImage = postService.getImageByPrimaryKey(idx);
        log.debug("유저가 요청한 이미지 : {}", testImage);
        return testImage;
    }

    @PostMapping("/createPost")
    public ResponseEntity<Void> createPost(@RequestBody PostsData postsData){
        log.debug("postData : {}", postsData.toString());
        postsData.setPostCreationDate(new Timestamp(System.currentTimeMillis()));
        log.debug("postData : {}", postsData);
        Integer result = postService.createPost(postsData);
        if(result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/createPostByClubId")
    public ResponseEntity<Void> createPostByClubId(@RequestBody ClubPostParam clubPostParam){
        log.debug("{}", clubPostParam);
        BoardId boardId = postService.getBoardIdByClubId(clubPostParam.getClubId());
        if(boardId != null){
            clubPostParam.setPostBoardId(boardId.getBoardId());
            clubPostParam.setPostCreationDate(new Timestamp(System.currentTimeMillis()));
            log.debug("{}", clubPostParam);
            Integer result = postService.createPost(clubPostParam);
            if(result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/updatePostByPostId")
    public ResponseEntity<Void> updatePostByPostId(@RequestBody PostsData postsData){
        log.debug("updatePostByPostId 게시물 수정 : {}", postsData);
        if (postsData != null){
            if (postService.getPostByBoardId(new BoardId(postsData.getPostBoardId())) != null){
                Integer result = postService.updatePostByPostId(postsData);
                if (result.equals(1)) return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/deletePost")
    public ResponseEntity<Void> deletePost(@RequestBody PostsData postsData){
        log.debug("deletePost 게시물 삭제를 실행합니다. {}", postsData);
        if (postsData != null){
            Integer deleteLikeResult = postService.deleteLikeByPostId(postsData);
            Integer deleteCommentResult = postService.deleteCommentByPostId(postsData);
            log.debug("연결된 좋아요와 댓글 삭제 완료.");
            Integer deletePostResult = postService.deletePost(postsData);
            if(deletePostResult.equals(1)) return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/createSuggestion")
    public ResponseEntity<Void> createSuggestion(@RequestBody PostsData postsData){
        log.debug("createSuggestion 건의하기 실행 {}",postsData);
        if (postsData != null){
            postsData.setPostBoardId(3);
            postsData.setPostTitle("건의사항");
            postsData.setPostCreationDate(new Timestamp(System.currentTimeMillis()));
            Integer result = postService.createPost(postsData);
            if (result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    댓글 관련
    @PostMapping("/createComment")
    public ResponseEntity<Void> createComment(@RequestBody PostComment postComment){
        log.debug("postComment : {}", postComment);
        postComment.setCommentCreationDate(new Timestamp(System.currentTimeMillis()));
        Integer result = (postComment.getCommentParentnum() == 0)?postService.createComment(postComment):postService.createReply(postComment);
        if(result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/createReply")
    public ResponseEntity<Void> createReply(@RequestBody PostComment postComment){
        log.debug("postComment : {}", postComment);
//        댓글 작성 시간 필요할 듯?
//        postComment.setCommentCreationDate(new Timestamp(System.currentTimeMillis()));
        Integer result = postService.createReply(postComment);
        if(result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/getAllCommentByPostId")
    public List<PostCommentData> getAllCommentByPostId(@RequestBody PostId postId){
        log.debug("댓글 불러올 게시물 id : {}", postId);
        List<PostCommentData> postCommentDataList = postService.getAllCommentByPostId(postId.getPostId());
        log.debug("불러온 댓글 : {}", postCommentDataList);
        return postCommentDataList;
    }

    @PostMapping("/deleteCommentByCommentId")
    public ResponseEntity<Void> deleteCommentByCommentId(@RequestBody PostComment postComment){
        log.debug("deleteCommentByCommentId 댓글 삭제 실행 {}", postComment);
        if (postComment != null){
            Integer deleteChildCommentResult = postService.deleteChildComment(postComment);
            Integer deleteCommentResult = postService.deleteCommentByCommentId(postComment);
            if (deleteCommentResult.equals(1)) return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    좋아요 관련
    @PostMapping("/postLike")
    public ResponseEntity<Void> postLike(@RequestBody PostLike postLike){
        log.debug("createLike : {}", postLike);
        if(postLike != null){
            PostLike postLikeResult = postService.getPostLike(postLike);
            log.debug("{}", postLikeResult);
            if(postLikeResult != null){
                log.debug("기존 like를 삭제합니다.");
                Integer result = postService.deleteLike(postLike);
                if (result.equals(1)) return ResponseEntity.status(HttpStatus.OK).build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        Integer result = postService.createLike(postLike);
        if (result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    이미지 연습
    @PostMapping("/imageCreateTest")
    public ResponseEntity<Void> imageCreateTest(@RequestParam MultipartFile imageSrc){
        try {
            log.debug("imageSrc : {}", imageSrc.getBytes());
            byte[] imageByte = Base64.getDecoder().decode(imageSrc.getBytes());
            log.debug("변환된 imageSrc : {}", imageByte);
        } catch (Exception e){

        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
