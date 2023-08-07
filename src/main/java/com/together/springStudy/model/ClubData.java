package com.together.springStudy.model;

import lombok.Data;

@Data
public class ClubData {
    int clubId;
    int clubLeaderId;
    int clubBoardId;
    String clubName;
    String clubDescription;
    boolean clubRecruiting;
}
