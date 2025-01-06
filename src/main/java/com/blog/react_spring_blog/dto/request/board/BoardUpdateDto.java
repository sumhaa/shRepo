package com.blog.react_spring_blog.dto.request.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateDto {
    private String title;
    private String content;

    public BoardUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
