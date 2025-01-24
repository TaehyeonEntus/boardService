package com.taehyeon.boardService.service;

import com.taehyeon.boardService.entity.Comment;
import com.taehyeon.boardService.entity.Post;
import com.taehyeon.boardService.repository.CommentRepository;
import com.taehyeon.boardService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    public void addPost(Post post) {
        postRepository.save(post);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void removePost(Post post) {
        postRepository.delete(post);
    }

    public void removeComment(Post post, Comment comment) {
        commentRepository.delete(comment);
    }

}
