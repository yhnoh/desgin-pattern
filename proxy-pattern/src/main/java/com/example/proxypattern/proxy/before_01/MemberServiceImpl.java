package com.example.proxypattern.proxy.before_01;

public class MemberServiceImpl implements MemberService{
    @Override
    public void join(String id) {

        long startTime = System.currentTimeMillis();
        System.out.println(id + "이 회원가입하였습니다.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }

    @Override
    public void delete(String id) {
        long startTime = System.currentTimeMillis();
        System.out.println(id + "이 탈퇴하였습니다.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }
}
