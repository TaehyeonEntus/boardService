package com.taehyeon.boardService.service;

import com.taehyeon.boardService.dto.PostSaveRequestDto;
import com.taehyeon.boardService.entity.Comment;
import com.taehyeon.boardService.entity.Member;
import com.taehyeon.boardService.entity.Post;
import com.taehyeon.boardService.exception.memberExceptions.MemberException;
import com.taehyeon.boardService.exception.memberExceptions.MemberNotFoundException;
import com.taehyeon.boardService.exception.postExceptions.PostException;
import com.taehyeon.boardService.exception.postExceptions.PostNotFoundException;
import com.taehyeon.boardService.repository.CommentRepository;
import com.taehyeon.boardService.repository.MemberRepository;
import com.taehyeon.boardService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    //게시글 추가
    public void addPost(PostSaveRequestDto postSaveRequestDto) throws MemberException{
        Long authorId = postSaveRequestDto.getAuthorId();
        String title = postSaveRequestDto.getTitle();
        String content = postSaveRequestDto.getContent();
        Member member = memberRepository
                .findById(authorId)
                .orElseThrow(MemberNotFoundException::new);
        postRepository.save(new Post(member, title, content));
    }

    public void addComment(Comment comment) {
        commentRepository
                .save(comment);
    }

    public Post findPostById(Long id) throws PostException {
        return postRepository
                .findById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    public List<Post> findAllPost(){
        return postRepository
                .findAll();
    }

    public void removePost(Post post) {
        postRepository
                .delete(post);
    }

    public void removeComment(Post post, Comment comment) {
        commentRepository
                .delete(comment);
    }

}
