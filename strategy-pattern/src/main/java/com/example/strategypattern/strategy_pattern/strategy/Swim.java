package com.example.strategypattern.strategy_pattern.strategy;

public class Swim implements Swimable{
    @Override
    public void swim() {
        System.out.println("수영한다.");
    }
}
