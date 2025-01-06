package com.blog.react_spring_blog.dto.response.file;

import com.blog.react_spring_blog.dto.response.board.ResBoardDetailsDto;
import com.blog.react_spring_blog.dto.response.board.ResBoardWriteDto;
import com.blog.react_spring_blog.entity.FileEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResBoardDetailsFileDto {
    private Long fileId;
    private String originFileName;
    private String fileType;

    @Builder
    public ResBoardDetailsFileDto(Long fileId, String originFileName, String fileType) {
        this.fileId = fileId;
        this.originFileName = originFileName;
        this.fileType = fileType;
    }

    public static ResBoardDetailsFileDto fromEntity(FileEntity file){
        return ResBoardDetailsFileDto.builder()
                .fileId(file.getId())
                .originFileName(file.getOriginFileName())
                .fileType(file.getFileType())
                .build();

    }
}
