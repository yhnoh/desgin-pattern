package com.example.strategypattern.strategy_pattern.strategy;

public class Quack implements Quackable{
    @Override
    public void quack() {
        System.out.println("울부짖다.");
    }
}
