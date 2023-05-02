package com.example.strategypattern.strategy_pattern.strategy;

public class Fly implements Flyable {
    @Override
    public void fly() {
        System.out.println("난다.");
    }
}
