package com.example.facadepattern.member;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberIdDoubleChecker {

    private final MemberRepository memberRepository;

    public MemberIdDoubleChecker(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void checkMemberId(String id){
        Optional<Member> findMember = memberRepository.findById(id);
        if(findMember.isPresent()){
            throw new IllegalArgumentException(id + "는 이미 사용중인 아이디입니다.");
        }
    }
}
