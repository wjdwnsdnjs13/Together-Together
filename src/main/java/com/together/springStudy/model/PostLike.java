package com.together.springStudy.model;

import lombok.Data;

@Data
public class PostLike {
    int likeId;
    int likeUserId;
    int likePostId;
}
