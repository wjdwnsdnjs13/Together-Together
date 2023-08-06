package com.together.springStudy.model;

import lombok.Data;

@Data
public class ClubMember {

    int clubMemberId;
    int memberUserId;
    int memberClubId;
    int memberPermission;
}
