package com.blog.react_spring_blog.service;

import com.blog.react_spring_blog.common.exception.ResourceNotFoundException;
import com.blog.react_spring_blog.dto.request.comment.CommentDto;
import com.blog.react_spring_blog.dto.response.comment.ResCommentDto;
import com.blog.react_spring_blog.entity.Board;
import com.blog.react_spring_blog.entity.Comment;
import com.blog.react_spring_blog.entity.Member;
import com.blog.react_spring_blog.repository.BoardRepository;
import com.blog.react_spring_blog.repository.CommentRepository;
import com.blog.react_spring_blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 댓글 조회
    public Page<ResCommentDto> getAllComments (Pageable pageable, Long boardId){
        Page<Comment> comments = commentRepository.findAllWithMemberAndBoard (pageable, boardId);
        List<ResCommentDto> commentList = comments.getContent().stream().map(ResCommentDto::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(commentList, pageable, comments.getTotalElements());
    }

    // 댓글 작성
    public ResCommentDto write(Long boardId, Member member, CommentDto writeDTO) {
        // 게시글 정보 검색
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ResourceNotFoundException("Board", "Board ID", String.valueOf(boardId))
        );

        // 댓글 작성자 정보 검색
        Member commentWriter = memberRepository.findById(member.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member ID", String.valueOf(member.getId()))
        );

        // 엔티티 변환, 연관관계 매핑
        Comment comment = CommentDto.ofEntity(writeDTO);
        comment.setBoard(board);
        comment.setMember(commentWriter);

        Comment saveComment = commentRepository.save(comment);

        return ResCommentDto.fromEntity(saveComment);
    }

    // 댓글 수정
    public ResCommentDto update(Long commentId, CommentDto commentDto){

        Comment comment = commentRepository.findByIdWithMemberAndBoard(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "Comment ID", String.valueOf(commentId))
        );
        comment.update(commentDto.getContent());
        return ResCommentDto.fromEntity(comment);
    }

    // 댓글 삭제
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
