package com.example.board.dto;

import lombok.Getter;

@Getter
public class CreateBoardRequestDto {

    private final String title;
    private final String contents;
    private final String username; // Cookie, Session, Token 을 활용할 수 있다.

    public CreateBoardRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
