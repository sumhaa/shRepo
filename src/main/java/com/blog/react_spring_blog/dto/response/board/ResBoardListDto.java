package com.blog.react_spring_blog.dto.response.board;

import com.blog.react_spring_blog.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResBoardListDto {

    private Long boardId;
    private String title;
    private String content;
    private String createdDate;
    private String modifiedDate;
    private String writerName;
    private int viewCount;

    @Builder
    public ResBoardListDto(Long boardId, String title, String content, String createdDate, String modifiedDate, String writerName, int viewCount) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.writerName = writerName;
        this.viewCount = viewCount;
    }

    public static ResBoardListDto fromEntity(Board board){
        return ResBoardListDto.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .build();
    }
}
