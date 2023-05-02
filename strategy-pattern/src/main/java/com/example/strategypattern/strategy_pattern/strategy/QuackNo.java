package com.example.strategypattern.strategy_pattern.strategy;

public class QuackNo implements Quackable{
    @Override
    public void quack() {
        System.out.println("울부짖지 못한다.");
    }
}
