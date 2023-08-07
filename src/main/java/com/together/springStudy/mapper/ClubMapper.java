package com.together.springStudy.mapper;

import com.together.springStudy.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClubMapper {
    int createClub(ClubData clubData);
    int createClubMaster(ClubMember clubMember);

    ClubData getClubByClubName(String clubName);

    List<ClubData> getAllClub();

    List<ClubData> getAffiliatedClub(int userId);

    List<ClubJoinQueue> getJoinClubQueue(int clubId);

    List<ClubMemberData> getClubMemberByClubId(int clubId);

    List<ClubData> getClubForKeyword(Keyword keyword);

    ClubData getClubByPrimaryKey(int clubId);
    int joinClub(ClubJoinQueue clubJoinQueue);

    int joinClubApproval(ClubJoinQueue clubJoinQueue);
    int deleteJoinClub(int joinQueueId);

    int updateClubRecruiting(ClubData clubData);
}
