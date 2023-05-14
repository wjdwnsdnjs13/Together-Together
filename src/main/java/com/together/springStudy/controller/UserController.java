package com.together.springStudy.controller;

import com.together.springStudy.model.UserData;
import com.together.springStudy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/together")
@RequiredArgsConstructor // 생성자 자동 생성
@Controller
public class UserController {
    UserData userData;
    @Autowired // 의존 관레를 자동으로 설정(service 객체 주입)
    UserService userService;

    @PostMapping("/signUp") //RequestMapping(Method=RequestMethod.POST)와 같음.
    public ResponseEntity<Void> signUp(@RequestBody UserData userData){ //ResponseEntity<Void>하면 body에 제네릭이 들어감
        Integer result = userService.joinUser(userData);
        if(result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build(); // 회원 가입 성공
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //회원 가입 실패
    }

    @GetMapping("/login")
    public ResponseEntity<Void> login(UserData userData){
        this.userData = userService.loginUser(userData);
        if(this.userData != null) return ResponseEntity.status(HttpStatus.OK).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
