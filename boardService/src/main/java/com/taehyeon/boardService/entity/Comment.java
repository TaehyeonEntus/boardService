package com.taehyeon.boardService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity // DB와 매핑될 객체인 것을 알려줌
@Table(name = "COMMENT")
@NoArgsConstructor
public class Comment {
    @Id //PK 지정
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String description;

    @Override
    public String toString() {
        return "Comment{" +
                "member=" + member +
                ", description='" + description + '\'' +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(Member member, Post post, String description) {
        this.post = post;
        this.member = member;
        this.description = description;
    }
}
