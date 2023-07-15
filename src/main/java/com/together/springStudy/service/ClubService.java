package com.together.springStudy.service;

import com.together.springStudy.mapper.ClubMapper;
import com.together.springStudy.model.ClubData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 생성자 자동 생성
public class ClubService {

    @Autowired
    ClubMapper clubMapper;

    @Transactional
    public Integer createClub(ClubData clubData){
        return clubMapper.createClub(clubData);
    }

    @Transactional
    public List<ClubData> getAllClub(){
        return clubMapper.getAllClub();
    }
}
