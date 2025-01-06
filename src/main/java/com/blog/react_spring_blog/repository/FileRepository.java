package com.blog.react_spring_blog.repository;

import com.blog.react_spring_blog.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

    @Query
    FileEntity findById(Long id);


    @Query
    FileEntity findByFileNameCustom(String fileName);


    @Query
    List<FileEntity> findAllFilesOrderByDate();
}
