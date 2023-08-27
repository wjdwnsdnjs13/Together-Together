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

    ClubJoinQueue getJoinClubQueueByUserIdAndClubId(ClubJoinQueue clubJoinQueue);

    List<ClubJoinQueueData> getJoinClubQueue(int clubId);

    List<ClubMemberData> getClubMemberByClubId(int clubId);

    List<ClubData> getClubForKeyword(Keyword keyword);

    ClubData getClubByPrimaryKey(int clubId);

    ClubData getClubByIdAndLeader(ClubData clubData);

    ClubId getLastClubId();

    int joinClub(ClubJoinQueue clubJoinQueue);

    int joinClubApproval(ClubJoinQueue clubJoinQueue);
    int updateJoinClubApproval(int joinQueueId);

    int joinClubRefusal(ClubJoinQueue clubJoinQueue);

    int updateClubRecruiting(ClubData clubData);

    int updateClubDescription(ClubData clubData);

    int deleteClubBoard(ClubData clubData);

    int deleteAllClubMember(ClubData clubData);

    int deleteClub(ClubData clubData);

    int withdrawalClub(ClubMember clubMember);


}
