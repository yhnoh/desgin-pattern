package com.example.factorypattern.based_interface;

public class RateDiscountPolicy implements DiscountPolicy{

    private double discountRate;

    public RateDiscountPolicy(double discountPrice) {
        this.discountRate = discountPrice;
    }

    @Override
    public Double discountPrice(Double originPrice) {
        return originPrice * discountRate / 100;
    }
}
