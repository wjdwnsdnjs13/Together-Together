package com.together.springStudy.model;

import lombok.Data;


@Data
public class ClubJoinQueue {
    int joinQueueId;
    int joinUserId;
    int joinClubId;
    String joinContent;
}
