package com.example.strategypattern.strategy_pattern;

import com.example.strategypattern.strategy_pattern.strategy.FlyNo;
import com.example.strategypattern.strategy_pattern.strategy.QuackNo;
import com.example.strategypattern.strategy_pattern.strategy.SwimNo;

//나무 오리 울기(X), 날기(X), 수영(X)
public class WoodDuck extends Duck {

    public WoodDuck() {
        super.setQuackable(new QuackNo());
        super.setFlyable(new FlyNo());
        super.setSwimable(new SwimNo());
    }

}
