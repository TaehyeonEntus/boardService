package com.taehyeon.boardService.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public class Member {
    @Id //PK 지정
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();
}
