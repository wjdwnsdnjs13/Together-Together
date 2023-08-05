package com.together.springStudy.mapper;

import com.together.springStudy.model.Keyword;
import com.together.springStudy.model.PostComment;
import com.together.springStudy.model.PostMainData;
import com.together.springStudy.model.PostsData;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
@MapperScan
public interface PostMapper {
//    post 관련
    List<PostMainData> getAllPostForMain();
    List<PostMainData> getPostForKeyword(Keyword keyword);
    int createPost(PostsData postsData);
    PostsData getByPostId(int postId);

//    댓글 관련
    int createComment(PostComment postComment);
    int createChildComment(PostComment postComment);
}
