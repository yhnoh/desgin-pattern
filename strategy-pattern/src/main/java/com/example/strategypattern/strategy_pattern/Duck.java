package com.example.strategypattern.strategy_pattern;

import com.example.strategypattern.strategy_pattern.strategy.Flyable;
import com.example.strategypattern.strategy_pattern.strategy.Quackable;
import com.example.strategypattern.strategy_pattern.strategy.Swimable;

/**
 * 오리의 추상 클래스
 */
public abstract class Duck {
    //행위 구성
    private Quackable quackable;
    private Flyable flyable;
    private Swimable swimable;

    //setter 메서드들을 통해서 언제든지 행위 변경 가능
    public void setQuackable(Quackable quackable) {
        this.quackable = quackable;
    }

    public void setFlyable(Flyable flyable) {
        this.flyable = flyable;
    }

    public void setSwimable(Swimable swimable) {
        this.swimable = swimable;
    }

    public void quack() {
        quackable.quack();
    }

    public void fly() {
        flyable.fly();
    }

    public void swim() {
        swimable.swim();
    }

}
