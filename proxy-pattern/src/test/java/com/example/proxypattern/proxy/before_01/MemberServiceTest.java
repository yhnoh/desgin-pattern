package com.example.proxypattern.proxy.before_01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    private MemberService memberService = new MemberServiceImpl();
    @Test
    void joinTest(){
        memberService.join("회원1");
    }

    @Test
    void deleteTest(){
        memberService.delete("회원1");
    }

}