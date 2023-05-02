package com.example.strategypattern.before;

//고무오리 울기(X), 날기(X), 수영(O)
public class RubberDuck extends Duck{
    @Override
    public void quack() {
        System.out.println("울부짖지 못한다.");
    }

    @Override
    public void fly() {
        System.out.println("날지 못한다.");
    }

}
