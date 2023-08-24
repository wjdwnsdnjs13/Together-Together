package com.together.springStudy.model;

import lombok.Data;

@Data
public class ClubJoinQueueData {
    int joinQueueId;
    int joinUserId;
    int joinClubId;
    String joinContent;
    String userNickname;
}
