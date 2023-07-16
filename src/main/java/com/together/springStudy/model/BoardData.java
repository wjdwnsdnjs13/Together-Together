package com.together.springStudy.model;

import lombok.Data;

@Data
public class BoardData {
    int boardId;
    String boardName;
    String boardDescription;
    String boardType;
}
