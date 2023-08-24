package com.together.springStudy.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SnsMainPageData {
    Date lastNoticeDate;
    List<PostMainData> snsMainPosts;
}
