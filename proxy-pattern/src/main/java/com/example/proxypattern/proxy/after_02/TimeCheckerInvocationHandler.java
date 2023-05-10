package com.example.proxypattern.proxy.after_02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeCheckerInvocationHandler implements InvocationHandler {

    private Object service;
    public TimeCheckerInvocationHandler(Object service) {
        this.service = service;
    }

    //실제 service의 메서드 실행 뿐만 아니라, 시간을 측정하는 부가로직도 들어가 있다.
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long startTime = System.currentTimeMillis();

        //InvocationHandler가 실제 클래스의 메서드를 실행한다.
        Object invoke = method.invoke(service, args);

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
        return invoke;
    }
}
