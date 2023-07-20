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

    //    컨트롤러에서 서비스 객체와 여기서 private final을 해주지 않으면
    //    Bean주입이 안된다고함. -> NullPointExection 뜸 bean 공부해보기..
    @Mapper
    private final PostMapper postMapper;

    @Transactional
    public List<PostMainData> getAllPost(){ return postMapper.getAllPost(); }

    @Transactional
    public Integer createPost(PostsData postsData){ return postMapper.createPost(postsData); }

    @Transactional
    public PostsData getByPostId(int postId){ return postMapper.getByPostId(postId); }

}
