package com.together.springStudy.service;

import com.together.springStudy.mapper.PostMapper;
import com.together.springStudy.model.PostMainData;
import com.together.springStudy.model.PostsData;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Mapper
    PostMapper postMapper;

    @Transactional
    public List<PostMainData> getAllPost(){ return postMapper.getAllPost(); }

    @Transactional
    public int createPost(PostsData postsData){ return postMapper.createPost(postsData); }

    @Transactional
    public PostsData getByPostId(int postId){ return postMapper.getByPostId(postId); }

}
