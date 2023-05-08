package com.example.proxypattern.proxy.after_01;


import java.util.function.Supplier;

public class MemberServiceProxy implements MemberService {

    private final MemberService memberService;
    public MemberServiceProxy(MemberService memberService) {
        this.memberService = memberService;
    }

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

        TimeChecker<Void> timeChecker = new TimeChecker<>();
        timeChecker.check(() -> {
            memberService.join(id);
            return null;
        });
    }

    @Override
    public void delete(String id) {
        TimeChecker<Void> timeChecker = new TimeChecker<>();
        timeChecker.check(() -> {
            memberService.delete(id);
            return null;
        });
    }
}
