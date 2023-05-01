package com.example.strategypattern;

//나무 오리 울기(X), 날기(X), 수영(X)
public class WoodDuck extends Duck{
    @Override
    public void quack() {
        System.out.println("울부짖지 못한다.");
    }

    @Override
    public void fly() {
        System.out.println("날지 못한다.");
    }

    @Override
    public void swim() {
        System.out.println("수영을 못한다.");
    }
}
