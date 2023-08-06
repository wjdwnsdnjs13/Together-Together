package com.together.springStudy.service;

import com.together.springStudy.mapper.ClubMapper;
import com.together.springStudy.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

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
    public Integer createClubMaster(ClubMember clubMember){
        return clubMapper.createClubMaster(clubMember);
    }

    @Transactional
    public List<ClubData> getAllClub(){
        return clubMapper.getAllClub();
    }

    @Transactional
    public List<ClubJoinQueue> getJoinClubQueue(int clubId) { return clubMapper.getJoinClubQueue(clubId); }

    @Transactional
    public ClubData getClubByPrimaryKey(int clubId) { return clubMapper.getClubByPrimaryKey(clubId); }

    @Transactional
    public List<ClubMemberData> getClubMemberByClubId(int clubId) { return clubMapper.getClubMemberByClubId(clubId); }

    @Transactional
    public List<ClubData> getClubForKeyword(Keyword keyword) { return clubMapper.getClubForKeyword(keyword); }

    @Transactional
    public Integer joinClub(ClubJoinQueue clubJoinQueue) { return clubMapper.joinClub(clubJoinQueue); }


    @Transactional
    public Integer joinClubApproval(ClubJoinQueue clubJoinQueue) {
        return clubMapper.joinClubApproval(clubJoinQueue);
    }
    @Transactional
    public Integer deleteJoinClub(int joinQueueId){
        return clubMapper.deleteJoinClub(joinQueueId);
    }

    @Transactional
    public Integer updateClubRecruiting(ClubData clubData){ return clubMapper.updateClubRecruiting(clubData); }

}
