package com.together.springStudy.mapper;

import com.together.springStudy.model.PostMainData;
import com.together.springStudy.model.PostsData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostMainData> getAllPost();
    int createPost(PostsData postsData);
    PostsData getByPostId(int postId);

}
