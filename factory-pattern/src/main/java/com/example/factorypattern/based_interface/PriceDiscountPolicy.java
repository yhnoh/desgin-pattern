package com.example.factorypattern.based_interface;

public class PriceDiscountPolicy implements DiscountPolicy{

    private double discountPrice;

    public PriceDiscountPolicy(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public Double discountPrice(Double originPrice) {
        return discountPrice;
    }
}
