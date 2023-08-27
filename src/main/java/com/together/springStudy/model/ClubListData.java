package com.together.springStudy.model;

import lombok.Data;

@Data
public class ClubListData {
    int clubId;
    int clubLeaderId;
    int clubBoardId;
    String clubName;
    String clubDescription;
    boolean clubRecruiting;
    String clubMasterNickname;
    int clubMemberCount;
}
