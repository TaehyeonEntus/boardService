package com.taehyeon.boardService.Controller;

import com.taehyeon.boardService.entity.Comment;
import com.taehyeon.boardService.entity.Member;
import com.taehyeon.boardService.entity.Post;
import com.taehyeon.boardService.service.BoardService;
import com.taehyeon.boardService.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;
    @PostMapping("/board/new")
    public void createPost(@RequestParam String title,
                           @RequestParam String name){
        Member member = memberService.getMemberByName(name);
        boardService.addPost(new Post(title, member));
    }

    @GetMapping("/board/{postId}")
    public void printPost(@PathVariable Long postId){
        Post post = boardService.getPostById(postId);
        System.out.println(post);
    }

    @GetMapping("/board/{postId}/newComment")
    public void createComment(@PathVariable Long postId, @RequestParam String name, @RequestParam String comment){
        Post post = boardService.getPostById(postId);
        Member member = memberService.getMemberByName(name);
        boardService.addComment(new Comment(post, member, comment));
    }
}
