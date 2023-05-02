package com.example.strategypattern.strategy_pattern;

import com.example.strategypattern.strategy_pattern.strategy.Fly;
import com.example.strategypattern.strategy_pattern.strategy.Quack;
import com.example.strategypattern.strategy_pattern.strategy.Swim;

//진짜 오리 울기(O), 날기(O), 수영(O)
public class RealDuck extends Duck {

    public RealDuck() {
        super.setQuackable(new Quack());
        super.setFlyable(new Fly());
        super.setSwimable(new Swim());
    }
}
