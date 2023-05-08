package com.example.proxypattern.proxy.after_01;

public class MemberServiceImpl implements MemberService {

    @Override
    public void join(String id) {
        System.out.println(id + "이 회원가입하였습니다.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

    }

    @Override
    public void delete(String id) {
        System.out.println(id + "이 탈퇴하였습니다.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
