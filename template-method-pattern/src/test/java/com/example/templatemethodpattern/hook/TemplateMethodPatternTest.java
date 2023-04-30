package com.example.templatemethodpattern.hook;

import org.junit.jupiter.api.Test;

public class TemplateMethodPatternTest {

    @Test
    void deliveryOrderTest(){
        System.out.println("======= 배달 주문 =======");
        Product product = new Product("돼지국밥", 8000, 10);
        DeliveryOrder deliveryOrder = new DeliveryOrder(product);
        deliveryOrder.order(5);


    }

    @Test
    void packageOrderTest(){
        System.out.println("======= 포장 주문 =======");
        Product product = new Product("돼지국밥", 8000, 10);
        PackageOrder packageOrder = new PackageOrder(product);
        packageOrder.order(5);

    }
}
