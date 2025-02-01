package com.taehyeon.boardService.controller;

import com.taehyeon.boardService.aop.LoggedInOnly;
import com.taehyeon.boardService.dto.PostSaveRequestDto;
import com.taehyeon.boardService.entity.Comment;
import com.taehyeon.boardService.entity.Member;
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

    @GetMapping("/board/new")
    public String showAddPostForm(Model model) {
        return "addPost";
    }

    @PostMapping("/board/new")
    public String createPost(@RequestParam String title,
                             @RequestParam String content,
                             HttpSession httpSession) throws MemberException {
        Long memberId = (Long) httpSession.getAttribute("memberId");
        boardService.addPost(new PostSaveRequestDto(memberId, title, content));
        return "redirect:/board";
    }

    @GetMapping("/board/{postId}")
    public String showPost(Model model,
                           @PathVariable Long postId) throws PostException {
        model.addAttribute("post", boardService.findPostById(postId));
        return "post";
    }

    @PostMapping("/board/{postId}/comment")
    public String createComment(@PathVariable Long postId,
                                HttpSession httpSession,
                                @RequestParam String commentContent) throws MemberException {
        //todo
        Post post = boardService.findPostById(postId);
        Long memberId = (Long) httpSession.getAttribute("memberId");
        Member member = memberService.findMemberByMemberId(memberId);

        boardService.addComment(new Comment(member,post,commentContent));

        return "redirect:/board/" + postId;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
