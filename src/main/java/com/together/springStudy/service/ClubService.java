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
    public ClubData getClubByClubName(String clubName) { return clubMapper.getClubByClubName(clubName); }

    @Transactional
    public List<ClubData> getAllClub(){
        return clubMapper.getAllClub();
    }

    @Transactional
    public List<ClubData> getAffiliatedClub(int userId) { return clubMapper.getAffiliatedClub(userId); }

    @Transactional
    public List<ClubJoinQueue> getJoinClubQueue(int clubId) { return clubMapper.getJoinClubQueue(clubId); }

    @Transactional
    public ClubData getClubByPrimaryKey(int clubId) { return clubMapper.getClubByPrimaryKey(clubId); }

    @Transactional
    public ClubData getClubByIdAndLeader(ClubData clubData) { return clubMapper.getClubByIdAndLeader(clubData); }

    @Transactional
    public List<ClubMemberData> getClubMemberByClubId(int clubId) { return clubMapper.getClubMemberByClubId(clubId); }

    @Transactional
    public List<ClubData> getClubForKeyword(Keyword keyword) { return clubMapper.getClubForKeyword(keyword); }

    @Transactional
    public ClubId getLastClubId(){ return clubMapper.getLastClubId(); }

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

    @Transactional
    public Integer updateClubDescription(ClubData clubData) { return clubMapper.updateClubDescription(clubData); }

    @Transactional
    public Integer deleteAllClubMember(ClubData clubData) { return clubMapper.deleteAllClubMember(clubData);}

    @Transactional
    public Integer deleteClub(ClubData clubData) { return clubMapper.deleteClub(clubData);}

    @Transactional
    public Integer withdrawalClub(ClubMember clubMember) { return clubMapper.withdrawalClub(clubMember);}

}
