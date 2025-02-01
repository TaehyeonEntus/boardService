package com.taehyeon.boardService.repository;

import com.taehyeon.boardService.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByNickname(String nickname);
}
