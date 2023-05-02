package com.example.strategypattern.strategy_pattern.strategy;

public class FlyNo implements Flyable{

    @Override
    public void fly() {
        System.out.println("날지 못한다.");
    }

}
