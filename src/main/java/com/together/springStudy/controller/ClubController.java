package com.together.springStudy.controller;

import com.together.springStudy.model.ClubData;
import com.together.springStudy.model.CreateClubData;
import com.together.springStudy.model.UserData;
import com.together.springStudy.service.ClubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/together/club")
@RequiredArgsConstructor
@RestController
public class ClubController {

    @Autowired
    ClubService clubService;

    @PostMapping("/createClub")
    public ResponseEntity<Void> createClub(@RequestBody CreateClubData createClubData){
        log.debug("createClubData info : {}", createClubData);
        Integer result = clubService.createClub(createClubData);
        if (result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getAllClub")
    public List<ClubData> getAllClub(){
        log.debug("getAllClub play.");
        List<ClubData> clubDataList = clubService.getAllClub();
        log.debug("club List : {}", clubDataList);
        return clubDataList;
//        clubDataList가 안 불러와졌을 때 Bad Request날릴 수 있어야할 듯?
//        if(clubDataList != null) return clubDataList;
//        else ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
