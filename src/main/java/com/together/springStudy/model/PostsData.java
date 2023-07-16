package com.together.springStudy.model;

import lombok.Data;

import java.util.Date;

@Data
public class PostsData {
    int postId;
    int postBoardId;
    int postUserId;
    String postTitle;
    String postContent;
    String postTag;
    Date postCreationDate;
}
