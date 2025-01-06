package com.blog.react_spring_blog.controller;

import com.blog.react_spring_blog.dto.request.board.BoardUpdateDto;
import com.blog.react_spring_blog.dto.request.board.BoardWriteDto;
import com.blog.react_spring_blog.dto.request.board.SearchData;
import com.blog.react_spring_blog.dto.response.board.ResBoardDetailsDto;
import com.blog.react_spring_blog.dto.response.board.ResBoardListDto;
import com.blog.react_spring_blog.dto.response.board.ResBoardWriteDto;
import com.blog.react_spring_blog.entity.Member;
import com.blog.react_spring_blog.service.BoardService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<Page<ResBoardListDto>> boardList(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {


        Page<ResBoardListDto> listDto = boardService.getAllBoard(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ResBoardListDto>> search(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                        @RequestParam String title,
                                                        @RequestParam String content,
                                                        @RequestParam String writerName){
        SearchData searchData = SearchData.createdSearchData(title, content, writerName);
        Page<ResBoardListDto> searchBoard = boardService.search(searchData, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(searchBoard);
    }

    @PostMapping("/write")
    public ResponseEntity<Page<ResBoardWriteDto>> write(@RequestBody BoardWriteDto boardDTO, @AuthenticationPrincipal Member member){

        // 스레드 등록
        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드: " + currentThread.getName());
        ResBoardWriteDto saveBoardDTO = boardService.write(boardDTO, member);

        return ResponseEntity.status(HttpStatus.CREATED).body(saveBoardDTO);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<ResBoardDetailsDto> detail(@PathVariable("boardId") Long boardId){
        ResBoardDetailsDto findBoardDTO = boardService.detail(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(findBoardDTO);
    }

    @GetMapping("/{boardId}/update")
    public ResponseEntity<ResBoardDetailsDto> update(@PathVariable Long boardId, @RequestBody BoardUpdateDto boardDTO){
        ResBoardDetailsDto updateBoardDTO = boardService.update(boardId, boardDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateBoardDTO);
    }

    @DeleteMapping("/{boardId}/delete")
    public ResponseEntity<Long> delete(@PathVariable Long boardId){
        boardService.delete(boardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
