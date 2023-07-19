package com.together.springStudy.model;

import lombok.Data;

import java.util.Date;

@Data
public class PostMainData {
    int postBoardId;
    int postUserId;
    String postTitle;
    String postContent;
    String postTag;
    Date postCreationDate;
    int postLikeCount;
    int postCommentCount;
}
