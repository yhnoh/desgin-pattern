package com.example.proxypattern.proxy.after_02;

import com.example.proxypattern.proxy.after_01.MemberService;
import com.example.proxypattern.proxy.after_01.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    @Test
    void joinTest(){
        //invocationHandler 생성
        TimeCheckerInvocationHandler timeCheckerInvocationHandler = new TimeCheckerInvocationHandler(new MemberServiceImpl());
        //Proxy 만들기
        MemberService memberService = (MemberService) Proxy.newProxyInstance(MemberService.class.getClassLoader(),
                new Class[]{MemberService.class},
                timeCheckerInvocationHandler);
        //Proxy 메서드 실행
        memberService.join("id");
    }

    @Test
    void deleteTest(){
        TimeCheckerInvocationHandler timeCheckerInvocationHandler = new TimeCheckerInvocationHandler(new MemberServiceImpl());

        MemberService memberService = (MemberService) Proxy.newProxyInstance(MemberService.class.getClassLoader(), new Class[]{MemberService.class}, timeCheckerInvocationHandler);
        memberService.delete("id");

    }

}