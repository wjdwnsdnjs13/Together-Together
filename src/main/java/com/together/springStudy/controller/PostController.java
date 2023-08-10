package com.together.springStudy.controller;

import com.together.springStudy.model.*;
import com.together.springStudy.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.sql.Timestamp;
import java.util.Date;
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
    @PostMapping("/getAllPostForMain")
    public List<PostMainData> getAllPostForMain(@RequestBody UserId userId){
        List<PostMainData> postMainDataList = postService.getAllPostForMain(userId.getUserId());
        log.debug("mainPost : {}", postMainDataList);
        return postMainDataList;
//        postMainDataList가 null일 경우 등에 대한 예외처리 해야할 듯?
    }

    @PostMapping("/getPostByPrimaryKey")
    public PostMainData getPostByPrimaryKey(@RequestBody PostId postId){
        log.debug("파라미터 : {}", postId);
        PostMainData postMainData = postService.getPostByPrimaryKey(postId);
        log.debug("postData : {}", postMainData);
        return postMainData;
    }

    @GetMapping("/getAllNotice")
    public List<PostMainData> getAllNotice(){
        List<PostMainData> postMainDataList = postService.getPostByBoardId(1);
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
        log.debug("{}", clubId);
        BoardId boardId = postService.getBoardIdByClubId(clubId.getClubId());
//        if(boardId != null)
        List<PostMainData> postMainDataList = postService.getPostByBoardId(boardId.getBoardId());
        log.debug("{}", postMainDataList);
        return postMainDataList;
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

//    좋아요 관련
    @PostMapping("/createLike")
    public ResponseEntity<Void> createLike(@RequestBody PostLike postLike){
        log.debug("createLike : {}", postLike);
        Integer result = postService.createLike(postLike);
        if (result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
