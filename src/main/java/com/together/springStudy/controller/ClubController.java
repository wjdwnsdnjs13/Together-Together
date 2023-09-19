package com.together.springStudy.controller;

import com.together.springStudy.model.*;
import com.together.springStudy.service.ClubService;
import com.together.springStudy.service.PostService;
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
@RequiredArgsConstructor //필드 주입을 하고 있는데, 왜 얘도 씀?
@RestController
public class ClubController {

    @Autowired
    ClubService clubService;

    @Autowired
    PostService postService;

    @PostMapping("/createClub")
    public ResponseEntity<Void> createClub(@RequestBody ClubData clubData){
        log.debug("createClubData info : {}", clubData);
        ClubData clubDataDB = clubService.getClubByClubName(clubData.getClubName());
        if (clubDataDB != null && clubDataDB.getClubName().equals(clubData.getClubName())) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        ClubId lastClubId = clubService.getLastClubId();
        if (lastClubId == null){
            lastClubId = new ClubId();
            lastClubId.setClubId(0);
        }
        log.debug("마지막 클럽 id : {}", lastClubId);
        BoardData boardData = new BoardData();
        boardData.setBoardName(clubData.getClubName() + "동아리 게시판");
        boardData.setBoardType(1);
        boardData.setBoardId((lastClubId.getClubId()) + 101);
        Integer boardCreateResult = postService.createBoardData(boardData);
        if(boardCreateResult.equals(1)){
            clubData.setClubBoardId((lastClubId.getClubId()) + 101);
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
    public List<ClubListData> getAllClub(){
        log.debug("getAllClub play.");
        List<ClubListData> clubListDataList = clubService.getAllClub();
        log.debug("club List : {}", clubListDataList);
        return clubListDataList;
//        clubDataList가 안 불러와졌을 때 Bad Request날릴 수 있어야할 듯?
//        if(clubDataList != null) return clubDataList;
//        else ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/getJoinClubQueue")
    public List<ClubJoinQueueData> getJoinClubQueue(@RequestBody ClubId clubId){
        log.debug("불러오려는 clubJoinQueue : {}", clubId);
        List<ClubJoinQueueData> clubJoinQueueDataList = clubService.getJoinClubQueue(clubId.getClubId());
        log.debug("클럽 가입 대기열 : {}", clubJoinQueueDataList);
        return clubJoinQueueDataList;
    }

    @PostMapping("/getClubMemberByClubId")
    public List<ClubMemberData> getClubMemberByClubId(@RequestBody ClubId clubId){
        log.debug("불러오려는 clubId : {}", clubId);
        List<ClubMemberData> clubMemberDataList = clubService.getClubMemberByClubId(clubId.getClubId());
        log.debug("클럽 멤버 : {}", clubMemberDataList);
        return clubMemberDataList;
    }

    @PostMapping("/getClubForKeyword")
    public List<ClubListData> getClubForKeyword(@RequestBody Keyword keyword){
        log.debug("키워드 : {}", keyword);
        List<ClubListData> clubListDataList = clubService.getClubForKeyword(keyword);
        log.debug("불러온 클럽 리스트 : {}", clubListDataList);
        return clubListDataList;
    }

//    가입된 동아리 목록 조회
    @PostMapping("/getAffiliatedClub")
    public List<ClubListData> getAffiliatedClub(@RequestBody UserId userId){
        log.debug("userId : {}", userId);
        List<ClubListData> clubListDataList = clubService.getAffiliatedClub(userId.getUserId());
        log.debug("동아리 목록 : {}", clubListDataList);
        return clubListDataList;
    }

    @PostMapping("/joinClub")
    public ResponseEntity<Void> joinClub(@RequestBody ClubJoinQueue clubJoinQueue){
        log.debug("joinClub : {}", clubJoinQueue);
        ClubJoinQueue clubJoinQueueDB = clubService.getJoinClubQueueByUserIdAndClubId(clubJoinQueue);
        if (clubJoinQueueDB != null){
            if ((clubJoinQueue.getJoinUserId() == clubJoinQueueDB.getJoinUserId()) && (clubJoinQueue.getJoinClubId() == clubJoinQueueDB.getJoinClubId())) {
                log.debug("이미 가입 요청을 넣은 상태입니다.");
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        Integer result = clubService.joinClub(clubJoinQueue);
        if(result.equals(1)) return ResponseEntity.status(HttpStatus.CREATED).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PostMapping("/joinClubApproval")
    public ResponseEntity<Void> joinClubApproval(@RequestBody ClubJoinQueue clubJoinQueue){
        log.debug("clubJoinQueue : {}", clubJoinQueue);
        Integer result = clubService.joinClubApproval(clubJoinQueue);
        if(result.equals(1)){
            log.debug("가입 처리 완료. 큐 상태 변화를 진행합니다.");
            Integer removeResult = clubService.updateJoinClubApproval(clubJoinQueue.getJoinQueueId());
            if(removeResult.equals(1)) {
                log.debug("큐 상태 변화를 완료했습니다.");
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/joinClubRefusal")
    public ResponseEntity<Void> joinClubRefusal(@RequestBody ClubJoinQueue clubJoinQueue){
        log.debug("joinClubRefusal 클럽 가입 거부를 실행합니다 {}", clubJoinQueue);
        Integer result = clubService.joinClubRefusal(clubJoinQueue);
        if (result.equals(1)){
            log.debug("가입 거부를 완료했습니다.");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/updateClubRecruiting")
    public ResponseEntity<Void> updateClubRecruiting(@RequestBody ClubData clubData){
        log.debug("현재 가입 여부 상태 : {}", clubData.isClubRecruiting());
        clubData.setClubRecruiting(!clubData.isClubRecruiting());
        Integer result = clubService.updateClubRecruiting(clubData);
        if(result.equals(1)) {
            log.debug("변경이 완료 되었습니다 현재 상태 : {}", clubData.isClubRecruiting());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/updateClubDescription")
    public ResponseEntity<Void> updateClubDescription(@RequestBody ClubData clubData){
        log.debug("updateClubDescription 클럽 설명 변경 : {}", clubData);
        if (clubData != null){
            Integer result = clubService.updateClubDescription(clubData);
            if (result.equals(1)) return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/deleteClub")
    public ResponseEntity<Void> deleteClub(@RequestBody ClubData clubData){
        log.debug("deleteClub을 실행합니다. {}", clubData);
        if(clubData != null){
            ClubData dbClubData = clubService.getClubByIdAndLeader(clubData);
            if((dbClubData.getClubId() == clubData.getClubId()) && (dbClubData.getClubLeaderId() == clubData.getClubLeaderId())){
                clubData.setClubBoardId(postService.getBoardIdByClubId(clubData.getClubId()).getBoardId());
                Integer boardDeleteResult = clubService.deleteClubBoard(clubData);
                Integer memberDeleteResult = clubService.deleteAllClubMember(clubData);
                Integer clubDeleteResult = clubService.deleteClub(clubData);
                log.debug("클럽 삭제 완료되었습니다.");
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/withdrawalClub")
    public ResponseEntity<Void> withdrawalClub(@RequestBody ClubMember clubMember){
        log.debug("withdrawalClub 클럽 탈퇴를 실행합니다. {}", clubMember);
        if(clubMember != null){
            Integer result = clubService.withdrawalClub(clubMember);
            Integer queueResult = clubService.deleteQueue(clubMember);
            if(result.equals(1)) {
                log.debug("탈퇴 완료 {}", result);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
