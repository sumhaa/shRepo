package com.blog.react_spring_blog.dto.response.board;

import com.blog.react_spring_blog.dto.response.comment.ResCommentDto;
import com.blog.react_spring_blog.dto.response.file.ResBoardDetailsFileDto;
import com.blog.react_spring_blog.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ResBoardDetailsDto {

    private Long boardId;
    private String title;
    private String content;
    private String writerName;
    private String createdDate;
    private String modifiedDate;
    private int viewCount;

    private List<ResCommentDto> comments;
    private List<ResBoardDetailsFileDto> files;

    @Builder
    public ResBoardDetailsDto(Long boardId, String title, String content, String writerName, String createdDate, String modifiedDate, int viewCount, List<ResCommentDto> comments, List<ResBoardDetailsFileDto> files) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.viewCount = viewCount;
        this.comments = comments;
        this.files = files;
    }

    public static ResBoardDetailsDto fromEntity(Board board){
        return ResBoardDetailsDto.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writerName(board.getMember().getUsername())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .viewCount(board.getViewCount())
                .comments(board.getComments().stream().map(ResCommentDto::fromEntity).collect(Collectors.toList()))
                .files(board.getFiles().stream().map(ResBoardDetailsFileDto::fromEntity).collect(Collectors.toList()))
                .build();
    }
}
