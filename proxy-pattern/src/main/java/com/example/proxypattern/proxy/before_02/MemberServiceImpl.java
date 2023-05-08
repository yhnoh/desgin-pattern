package com.example.proxypattern.proxy.before_02;

import java.util.function.Supplier;

public class MemberServiceImpl implements MemberService {

    public static class TimeChecker<T>{
        public T check(Supplier<T> supplier){
            long startTime = System.currentTimeMillis();
            T result = supplier.get();
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime + "ms");
            return result;
        }
    }

    @Override
    public void join(String id) {

        TimeChecker<Object> timeChecker = new TimeChecker<>();
        timeChecker.check(() -> {
            System.out.println(id + "이 회원가입하였습니다.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        });
    }

    @Override
    public void delete(String id) {

        TimeChecker<Object> timeChecker = new TimeChecker<>();
        timeChecker.check(() -> {
            System.out.println(id + "이 탈퇴하였습니다.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        });
    }
}
