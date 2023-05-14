package com.together.springStudy.model;

import lombok.Data;

import java.util.Date;

@Data
public class VolunteerList {
    int volId;
    String volTitle;
    String volContent;
    String volHost;
    String volUrl;
    String volCategory;
    String volLocation;
    String volLocationStr;
    Date volStime;
    Date volEdtime;
}
