package com.example.torder.repository;

import com.example.torder.domain.Member;

public interface MemberRepository {
    void existMembersave();

    boolean loginCheck(Member member);

    Member save(Member member);

    boolean findById(String id);

    boolean findByNickname(String nickname);
}
