package com.taehyeon.boardService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity // DB와 매핑될 객체인 것을 알려줌
@Table(name="LOCAL_MEMBER")
@DiscriminatorValue("L")
public class LocalMember extends Member{
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @Min(6)
    private String password;
}
