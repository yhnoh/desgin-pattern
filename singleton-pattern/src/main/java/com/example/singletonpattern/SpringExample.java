package com.example.singletonpattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringExample {

    /**
     * 엄연히 말하자면 싱글톤 패턴이 아니다.
     * 빈 후처리기 같은 곳에서, 변경시킬 수 있기 때문
     *
     *
     */

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        String hello1 = context.getBean("hello", String.class);
        String hello2 = context.getBean("hello", String.class);
        System.out.println(hello1 == hello2);
    }
}
