package com.together.springStudy.controller;

import com.together.springStudy.model.PostMainData;
import com.together.springStudy.model.PostsData;
import com.together.springStudy.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    PostService postService;

    @GetMapping("/getAllPost")
    public List<PostMainData> getAllPost(){
        List<PostMainData> postMainDataList = postService.getAllPost();
        log.debug("mainPost : {}", postMainDataList);
        return postMainDataList;
//        postMainDataList가 null일 경우 등에 대한 예외처리 해야할 듯?
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
}
