package com.together.springStudy.model;

import lombok.Data;

@Data
public class PostComment {
    int commentId;
    int commentUserId;
    int commentPostId;
    int commentParentnum;
    String commentContent;
}
