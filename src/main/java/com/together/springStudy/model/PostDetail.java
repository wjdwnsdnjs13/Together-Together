package com.together.springStudy.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDetail {
    int postId;
    int postBoardId;
    int postUserId;
    String postTitle;
    String postContent;
    String postTag;
    Date postCreationDate;
    List<PostComment> postComments;
    int postLikeCount;

}
