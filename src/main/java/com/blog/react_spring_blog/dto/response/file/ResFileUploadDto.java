package com.blog.react_spring_blog.dto.response.file;

import com.blog.react_spring_blog.entity.FileEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResFileUploadDto {

    //아이디, 기존 파일이름, 파일 경로, 파일 타입
    private Long fileId;
    private String originFileName;
    private String filePath;
    private String fileType;

    @Builder
    public ResFileUploadDto(Long fileId, String originFileName, String filePath, String fileType) {
        this.fileId = fileId;
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public static ResFileUploadDto fromEntity(FileEntity file){
        return ResFileUploadDto.builder()
                .fileId(file.getId())
                .originFileName(file.getOriginFileName())
                .filePath(file.getFilePath())
                .fileType(file.getFileType())
                .build();
    }
}
