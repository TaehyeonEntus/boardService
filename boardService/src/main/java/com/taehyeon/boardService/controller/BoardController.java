package com.taehyeon.boardService.controller;

import com.taehyeon.boardService.aop.LoggedInOnly;
import com.taehyeon.boardService.dto.PostSaveRequestDto;
import com.taehyeon.boardService.entity.Post;
import com.taehyeon.boardService.exception.memberExceptions.MemberException;
import com.taehyeon.boardService.exception.postExceptions.PostException;
import com.taehyeon.boardService.service.BoardService;
import com.taehyeon.boardService.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@LoggedInOnly
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/board")
    public String showBoard(Model model) {
        List<Post> posts = boardService.findAllPost();
        model.addAttribute("posts", posts);
        return "board";
    }

    @PostMapping("/board/new")
    public String createPost(@RequestParam String title,
                             @RequestParam String content,
                             @RequestParam Long authorId) throws MemberException {
        boardService.addPost(new PostSaveRequestDto(authorId, title, content));
        return "redirect:/board";
    }

    @GetMapping("/board/{postId}")
    public String showPost(Model model,
                           @PathVariable Long postId) throws PostException {
        model.addAttribute("post", boardService.findPostById(postId));
        return "post";
    }

    @PostMapping("/board/{postId}/addComment")
    public String createComment(@PathVariable Long postId,
                                @RequestParam String name,
                                @RequestParam String comment) throws MemberException {
        //todo
        return "redirect:/board/" + postId;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
