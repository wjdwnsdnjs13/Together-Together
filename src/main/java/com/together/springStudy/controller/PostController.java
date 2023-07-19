package com.together.springStudy.controller;

import com.together.springStudy.model.PostMainData;
import com.together.springStudy.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/together/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/getAllPost")
    public List<PostMainData> getAllPost(){
        List<PostMainData> postMainDataList = postService.getAllPost();
        log.debug("mainPost : {}", postMainDataList);
        return postMainDataList;
//        postMainDataList가 null일 경우 등에 대한 예외처리 해야할 듯?
    }
}
