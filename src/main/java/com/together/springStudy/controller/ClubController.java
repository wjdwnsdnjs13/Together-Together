package com.together.springStudy.controller;

import com.together.springStudy.model.ClubData;
import com.together.springStudy.model.UserData;
import com.together.springStudy.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/together/club")
@Controller
@Slf4j
public class ClubController {

    @Autowired
    ClubService clubService;

    @GetMapping("/createClub")
    public ResponseEntity<Void> createClub(ClubData clubData, Integer clubLeaderDid){
        log.debug("club info : {}", clubData);
        log.debug("club leader : {}", clubLeaderDid);
        Integer result = clubService.createClub(clubData);
        if (result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getAllClub")
    public List<ClubData> getAllClub(){
        List<ClubData> clubDataList = clubService.getAllClub();
        return clubDataList;
//        clubDataList가 안 불러와졌을 때 Bad Request날릴 수 있어야할 듯?
//        if(clubDataList != null) return clubDataList;
//        else ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
