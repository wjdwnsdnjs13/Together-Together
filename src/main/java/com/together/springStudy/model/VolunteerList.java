package com.together.springStudy.model;

import lombok.Data;

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
    String volStime;
    String volEdtime;
}
