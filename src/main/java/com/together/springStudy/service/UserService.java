package com.together.springStudy.service;

import com.together.springStudy.mapper.UserMapper;
import com.together.springStudy.model.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 생성자 자동 생성
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Transactional // 트랜잭션 보장이 된 메소드로 설정해줌.
    public int joinUser(UserData userData){
        return userMapper.register(userData);
    }

    @Transactional
    public UserData loginUser(UserData userData){
        return userMapper.login(userData);
    }
}
