package com.blog.react_spring_blog.dto.request.board;

import com.blog.react_spring_blog.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardWriteDto {
    private Long boardId;
    private String title;

    public BoardWriteDto(Long boardId, String title) {
        this.boardId = boardId;
        this.title = title;
    }

    @Builder
    public static Board ofEntity(BoardWriteDto dto){
        return Board.builder()
                .title()
                .build();
    }
}
