package com.together.springStudy.model;

import lombok.Data;

@Data
public class BoardId {
    int boardId;
    int userId;

    public BoardId(){};

    public BoardId(int boardId){
        this.boardId = boardId;
    }

    public BoardId(int boardId, int userId){
        this.boardId = boardId;
        this.userId = userId;
    }

}
