package com.example.strategypattern.strategy_pattern;

import org.junit.jupiter.api.Test;

public class StrategyPatternTest {

    @Test
    void strategyTest(){
        System.out.println("========진짜 오리========");
        Duck realDuck = new RealDuck();
        realDuck.fly();
        realDuck.swim();
        realDuck.quack();

        System.out.println();
        System.out.println("========고무 오리========");
        Duck rubberDuck = new RubberDuck();
        rubberDuck.fly();
        rubberDuck.swim();
        rubberDuck.quack();

        System.out.println();
        System.out.println("========나무 오리========");
        Duck woodDuck = new WoodDuck();
        woodDuck.fly();
        woodDuck.swim();
        woodDuck.quack();

    }
}
