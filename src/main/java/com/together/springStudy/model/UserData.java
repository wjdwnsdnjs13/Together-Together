package com.together.springStudy.model;


import lombok.Data;

import java.util.Date;

@Data
public class UserData {
    int userId;
    String userAcid;
    String userEmail;
    String userPw;
    String userName;
    String userNickName;
    int userGender;
    Date userBirthdate;
    String userProfileImage;
    String userDef;
    String userType;
    String userSns;
}
