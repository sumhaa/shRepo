package com.blog.react_spring_blog.controller;

import com.blog.react_spring_blog.dto.response.file.ResFileDownloadDto;
import com.blog.react_spring_blog.dto.response.file.ResFileUploadDto;
import com.blog.react_spring_blog.service.FileService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/board/{boardId}/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    // 다운로드 - 조회
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("fileId") Long fileId) throws IOException {
        ResFileDownloadDto downloadDto = fileService.download(fileId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(downloadDto.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "" + downloadDto.getFilename() + "\")

    }

    // 업로드
    @PostMapping("/upload")
    public ResponseEntity<List<ResFileUploadDto>> upload(@PathVariable Long boardId,
                                                         @RequestParam("file")List<MultipartFile> files) throws IOException {
        List<ResFileUploadDto> saveFile = fileService.upload(boardId, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveFile);
    }

    // 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<> delete(@RequestParam("fileId") Long fileId){

        fileService.delete(fileId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
