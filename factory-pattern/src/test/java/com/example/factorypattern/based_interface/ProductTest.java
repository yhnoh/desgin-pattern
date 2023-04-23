package com.example.factorypattern.based_interface;

import com.example.factorypattern.based_interface.Product;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    void orderByDiscountPriceTest() {

        Product product = new Product(10000);
        product.orderByDiscountPrice(1000);
    }

    @Test
    void orderByDiscountRateTest(){

        Product product = new Product(10000);
        product.orderByDiscountRate(10);
    }

}
