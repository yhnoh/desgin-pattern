package com.example.strategypattern.strategy_pattern.strategy;

public class SwimNo implements Swimable{

    @Override
    public void swim() {
        System.out.println("수영을 못한다.");
    }
}
