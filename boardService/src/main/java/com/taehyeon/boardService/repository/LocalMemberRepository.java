package com.taehyeon.boardService.repository;

import com.taehyeon.boardService.entity.LocalMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalMemberRepository extends JpaRepository<LocalMember, Long> {
    LocalMember findByUsername(String username);
}
