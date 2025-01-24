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

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", member=" + member +
                ", comments=" + comments +
                '}';
    }

    public Post(String title, Member member) {
        this.title = title;
        this.member = member;
    }
}
