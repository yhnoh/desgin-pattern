package com.example.strategypattern.strategy_pattern;

import com.example.strategypattern.strategy_pattern.strategy.*;

//고무오리 울기(X), 날기(X), 수영(O)
public class RubberDuck extends Duck {

    public RubberDuck() {
        super.setQuackable(new Quack());
        super.setFlyable(new FlyNo());
        super.setSwimable(new SwimNo());
    }

}
