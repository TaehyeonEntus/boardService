package com.taehyeon.boardService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public class Member {
    @Id //PK 지정
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    protected Long id;

    @Column(nullable = false, unique = true)
    protected String nickname;

    @OneToMany(mappedBy = "member")
    protected List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    protected Role role;

    @OneToMany(mappedBy = "member")
    protected List<Post> posts = new ArrayList<>();
}
