package com.together.springStudy.service;

import com.together.springStudy.mapper.ClubMapper;
import com.together.springStudy.model.ClubData;
import com.together.springStudy.model.ClubJoinQueue;
import com.together.springStudy.model.ClubMember;
import com.together.springStudy.model.CreateClubData;
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
    public Integer createClub(CreateClubData createClubData){
        return clubMapper.createClub(createClubData);
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
    public Integer joinClub(ClubJoinQueue clubJoinQueue) { return clubMapper.joinClub(clubJoinQueue); }

    @Transactional
    public Integer joinClubApproval(ClubJoinQueue clubJoinQueue) {
        return clubMapper.joinClubApproval(clubJoinQueue);
    }
    @Transactional
    public Integer deleteJoinClub(int joinQueueId){
        return clubMapper.deleteJoinClub(joinQueueId);
    }

}
