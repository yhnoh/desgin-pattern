package com.example.strategypattern.before;

/**
 * 오리의 추상 클래스
 */
public abstract class Duck {

    public void quack() {
        System.out.println("울부짖다.");
    }

    public void fly() {
        System.out.println("난다.");
    }

    public void swim() {
        System.out.println("수영한다.");
    }

}
