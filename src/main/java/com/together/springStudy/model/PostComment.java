package com.together.springStudy.model;

import lombok.Data;

import java.util.Date;

@Data
public class PostComment {
    int commentId;
    int commentUserId;
    int commentPostId;
    int commentParentnum;
    String commentContent;
    Date commentCreationDate;

}
