package com.together.springStudy.mapper;

import com.together.springStudy.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
@MapperScan
public interface PostMapper {
//    post 관련
    List<PostMainData> getAllPostForMain();

    PostMainData getPostByPrimaryKey(int postId);

    List<PostMainData> getPostByBoardId(int postBoardId);

    List<PostMainData> getPostForKeyword(Keyword keyword);
    int createPost(PostsData postsData);
    List<PostMainData> getPostsByUserId(int userId);

//    댓글 관련
    int createComment(PostComment postComment);
    int createReply(PostComment postComment);

    List<PostCommentData> getAllCommentByPostId(int postId);
}
