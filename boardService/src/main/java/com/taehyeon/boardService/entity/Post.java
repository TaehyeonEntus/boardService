package com.taehyeon.boardService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity // DB와 매핑될 객체인 것을 알려줌
@Table(name="POST")
@NoArgsConstructor
public class Post {
    @Id //PK 지정
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }
}
