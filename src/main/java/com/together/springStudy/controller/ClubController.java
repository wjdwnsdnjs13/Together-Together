package com.together.springStudy.controller;

import com.together.springStudy.model.*;
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
    public ResponseEntity<Void> createClub(@RequestBody ClubData clubData){
        log.debug("createClubData info : {}", clubData);
        ClubId lastClubId = clubService.getLastClubId();
        log.debug("마지막 클럽 id : {}", lastClubId);
        clubData.setClubBoardId((lastClubId.getClubId() * 10) + 1);
        Integer result = clubService.createClub(clubData);
        if (result.equals(1)) {
                if( clubData.getClubId() != 0){
                    ClubMember clubMember = new ClubMember();
                    log.debug("clubMember 객체 생성 확인 : {}", clubMember);
                    clubMember.setMemberClubId(clubData.getClubId());
                    clubMember.setMemberUserId(clubData.getClubLeaderId());
                    clubMember.setMemberPermission(999);
                    log.debug("clubMember 객체 데이터 확인 : {}", clubMember);
                    Integer masterCreateResult = clubService.createClubMaster(clubMember);
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/getClubByClubName")
    public ResponseEntity<Void> getClubByClubName(@RequestBody ClubName clubName){
        log.debug("ClubName : {}", clubName);
        ClubData clubData = clubService.getClubByClubName(clubName.getClubName());
        log.debug("불러온 clubData : {}",clubData);
        if(clubData == null || !clubData.getClubName().toLowerCase().equals(clubName.getClubName().toLowerCase())){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else return ResponseEntity.status(HttpStatus.CONFLICT).build();
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

    @PostMapping("/getJoinClubQueue")
    public List<ClubJoinQueue> getJoinClubQueue(@RequestBody ClubId clubId){
        log.debug("불러오려는 clubJoinQueue : {}", clubId);
        List<ClubJoinQueue> clubJoinQueueList = clubService.getJoinClubQueue(clubId.getClubId());
        log.debug("클럽 가입 대기열 : {}", clubJoinQueueList);
        return clubJoinQueueList;
    }

    @PostMapping("/getClubMemberByClubId")
    public List<ClubMemberData> getClubMemberByClubId(@RequestBody ClubId clubId){
        log.debug("불러오려는 clubId : {}", clubId);
        List<ClubMemberData> clubMemberDataList = clubService.getClubMemberByClubId(clubId.getClubId());
        log.debug("클럽 멤버 : {}", clubMemberDataList);
        return clubMemberDataList;
    }

    @PostMapping("/getClubForKeyword")
    public List<ClubData> getClubForKeyword(@RequestBody Keyword keyword){
        log.debug("키워드 : {}", keyword);
        List<ClubData> clubDataList = clubService.getClubForKeyword(keyword);
        log.debug("불러온 클럽 리스트 : {}", clubDataList);
        return clubDataList;
    }

//    가입된 동아리 목록 조회
    @PostMapping("/getAffiliatedClub")
    public List<ClubData> getAffiliatedClub(@RequestBody UserId userId){
        log.debug("userId : {}", userId);
        List<ClubData> clubDataList = clubService.getAffiliatedClub(userId.getUserId());
        log.debug("동아리 목록 : {}", clubDataList);
        return clubDataList;
    }

    @PostMapping("/joinClub")
    public ResponseEntity<Void> joinClub(@RequestBody ClubJoinQueue clubJoinQueue){
        log.debug("joinClub : {}", clubJoinQueue);
        Integer result = clubService.joinClub(clubJoinQueue);
        if(result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PostMapping("/joinClubApproval")
    public ResponseEntity<Void> joinClubApproval(@RequestBody ClubJoinQueue clubJoinQueue){
        log.debug("clubJoinQueue : {}", clubJoinQueue);
        Integer result = clubService.joinClubApproval(clubJoinQueue);
        if(result.equals(1)){
            log.debug("가입 처리 완료. 큐에서 삭제를 진행합니다.");
            Integer removeResult = clubService.deleteJoinClub(clubJoinQueue.getJoinQueueId());
            if(removeResult.equals(1)) {
                log.debug("큐에서 삭제를 완료했습니다.");
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/updateClubRecruiting")
    public ResponseEntity<Void> updateClubRecruiting(@RequestBody ClubData clubData){
        log.debug("현재 가입 여부 상태 : {}", clubData.isClubRecruiting());
        clubData.setClubRecruiting(!clubData.isClubRecruiting());
        log.debug("바뀐 가입 여부 상태 : {}", clubData.isClubRecruiting());
        Integer result = clubService.updateClubRecruiting(clubData);
        if(result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
