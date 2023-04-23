package com.example.factorypattern.based_interface;

public class Product {

    private double originPrice;

    public Product(double originPrice) {
        this.originPrice = originPrice;
    }

    public void orderByDiscountPrice(double discount){
        PriceDiscountPolicy priceDiscountPolicy = new PriceDiscountPolicy(discount);
        Double discountPrice = priceDiscountPolicy.discountPrice(originPrice);

        System.out.println("상품 원가 = " + originPrice);
        System.out.println("상품 할인가 = " + discountPrice);
        System.out.println("상품 주문가 = " + (originPrice - discountPrice));
    }

    public void orderByDiscountRate(double discount){
        RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy(discount);
        Double discountPrice = rateDiscountPolicy.discountPrice(originPrice);

        System.out.println("상품 원가 = " + originPrice);
        System.out.println("상품 할인가 = " + discountPrice);
        System.out.println("상품 주문가 = " + (originPrice - discountPrice));
    }

    public void order(DiscountPolicy discountPolicy){
        System.out.println("상품 원가 = " + originPrice);
        Double discountPrice = discountPolicy.discountPrice(originPrice);
        System.out.println("상품 할인가 = " + discountPrice);
        System.out.println("상품 주문가 = " + (originPrice - discountPrice));
    }
}
