package com.together.springStudy.model;

import lombok.Data;

import java.util.Date;

@Data
public class ClubPostParam extends PostsData{
    int postId;
    int postBoardId;
    int postUserId;
    String postTitle;
    String postContent;
    String postTag;
    Date postCreationDate;
    int clubId;
    int postType;
}
