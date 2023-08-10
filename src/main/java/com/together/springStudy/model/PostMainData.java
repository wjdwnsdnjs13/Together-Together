package com.together.springStudy.model;

import lombok.Data;

import java.util.Date;

@Data
public class PostMainData {
    int postId;
    int postBoardId;
    int postUserId;
    String userNickname;
    String postTitle;
    String postContent;
    String postTag;
    Date postCreationDate;
    int postLikeCount;
    int postCommentCount;
    int postLikeId;
}
