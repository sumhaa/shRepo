package com.blog.react_spring_blog.service;

import com.blog.react_spring_blog.common.exception.ResourceNotFoundException;
import com.blog.react_spring_blog.dto.response.file.ResFileDownloadDto;
import com.blog.react_spring_blog.dto.response.file.ResFileUploadDto;
import com.blog.react_spring_blog.entity.Board;
import com.blog.react_spring_blog.entity.FileEntity;
import com.blog.react_spring_blog.repository.BoardRepository;
import com.blog.react_spring_blog.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    @Value("${project.folderPath}")
    private String FOLDER_PATH;

    // 업로드
    public List<ResFileUploadDto> upload(Long boardId, List<MultipartFile> multipartFiles) throws IOException {

        // 게시글 찾기
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ResourceNotFoundException("Board", "Board Id", String.valueOf(boardId))
        );

        // 원본 파일 이름 가져오기
        List<MultipartFile> fileEntitys = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles){

            // 폴더에 업로드하는 동안 이미지의 무작위 이름 생성
            String fileName = multipartFile.getOriginalFilename();
            String randomId = UUID.randomUUID().toString();

            String filePath = "POST_" + board.getId() + "_" + randomId.concat(fileName.substring(fileName.indexOf(".")));

            // File.separator : OS에 따른 구분자
            String fileResourcePath = FOLDER_PATH + File.separator + filePath;

            // 폴더가 생성되지 않은 경우 폴더를 생성
            File f = new File(FOLDER_PATH);
            if (!f.exists()){
                f.mkdir();
            }

            Files.copy(multipartFile.getInputStream(), Paths.get(fileResourcePath));
            FileEntity saveFile = FileEntity.builder()
                    .originFileName(multipartFile.getOriginalFilename())
                    .filePath(filePath)
                    .fileType(multipartFile.getContentType())
                    .build();
            saveFile.setMappingBoard(board);
        }
        List <ResFileUploadDto> dtos =fileEntitys.stream()
                .map(ResFileUploadDto::fromEntity).collect(Collectors.toList())






        // 폴더가 생성되지 않은 경우 폴더를 생성

        // 폴더에 파일 복사
    }

    // 다운로드
    public ResFileDownloadDto downloadDto()


    // 삭제
    public void delete(Long fileId){
        FileEntity file = fileRepository.findById(fileId).orElseThrow(
                () -> new ResourceNotFoundException("File", "File Id", String.valueOf(fileId))
        );
        String filePath = FOLDER_PATH + File.separator + file.getFilePath();
        File physicalFile = new File(filePath);
        if (physicalFile.exists()){
            physicalFile.delete();
        }
        fileRepository
    }
}
