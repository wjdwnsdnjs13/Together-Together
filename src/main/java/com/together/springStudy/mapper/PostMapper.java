package com.together.springStudy.mapper;

import com.together.springStudy.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
@MapperScan
public interface PostMapper {
//    post 관련
    List<PostMainData> getAllPostForMain(int userId);

    PostMainData getLastNoticeDate();

    PostMainData getPostByPrimaryKey(PostId postId);

    List<PostMainData> getPostByBoardId(BoardId boardId);

    BoardId getBoardIdByClubId(int clubId);

    List<PostMainData> getPostForKeyword(Keyword keyword);
    int createPost(PostsData postsData);
    List<PostMainData> getPostsByUserId(int userId);

    List<PostMainData> getClubPostCreatedByUser(UserId userId);

    TestImage getImageByPrimaryKey(int idx);

    int deleteLikeByPostId(PostsData postsData);

    int deleteCommentByPostId(PostsData postsData);

    int deletePost(PostsData postsData);

    int updatePostByPostId(PostsData postsData);


//    댓글 관련
    int createComment(PostComment postComment);
    int createReply(PostComment postComment);

    List<PostCommentData> getAllCommentByPostId(int postId);

    int deleteChildComment(PostComment postComment);

    int deleteCommentByCommentId(PostComment postComment);

//    좋아요 관련
    int createLike(PostLike postLike);

    PostLike getPostLike(PostLike postLike);

    int deleteLike(PostLike postLike);

//    게시판 관련
    int createBoardData(BoardData boardData);
}
