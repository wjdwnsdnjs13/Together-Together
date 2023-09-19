package com.together.springStudy.model;

import lombok.Data;

@Data
public class PostImages {
    int postImageId;
    int postImageUserId;
    int postImageParentnum;
    byte[] postImageSrc;
}
