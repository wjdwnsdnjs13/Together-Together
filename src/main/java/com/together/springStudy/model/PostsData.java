package com.together.springStudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PostsData {
    int postId;
    int postBoardId;
    int postUserId;
    String postTitle;
    String postContent;
    String postTag;
    Date postCreationDate;

    public PostsData(){};

    public PostsData(int postId, int postUserId) {
        this.postId = postId;
        this.postUserId = postUserId;
    }

    public PostsData(int postBoardId, int postUserId, String postTitle, String postContent) {
        this.postBoardId = postBoardId;
        this.postUserId = postUserId;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }


}
