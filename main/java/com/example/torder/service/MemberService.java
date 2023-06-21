package com.example.torder.service;

import com.example.torder.domain.Member;
import com.example.torder.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 이전의 회원가입된 정보 저장 */
    public void existMembersave() {
        memberRepository.existMembersave();
    }

    /* 아이디, 비밀번호 일치하는지 확인 */
    public boolean Login(Member member) {
        return memberRepository.loginCheck(member);
    }

    /* 회원가입 db저장 */
    public String save(Member member) {
        System.out.println("db에 회원정보 저장합니다!!");
        memberRepository.save(member);
        return member.getId();
    }

    /* 회원가입 중복확인 */
    public void join(Member member) {
        validateDuplicateMember(member);
        System.out.println("** MemberService join 동작 **");
        System.out.println("member.id=" + member.getId());
        System.out.println("member.passward= " + member.getPassword());
        System.out.println("member.nickname= " + member.getNickname());
    }

    /* 회원 중복 검증 */
    private void validateDuplicateMember(Member member) {
        boolean result = memberRepository.findById(member.getId());
        if (result == true) {
            member.setId("");
        }

        result = memberRepository.findByNickname(member.getNickname());
        if (result == true) {
            member.setNickname("");
        }
    }
}
