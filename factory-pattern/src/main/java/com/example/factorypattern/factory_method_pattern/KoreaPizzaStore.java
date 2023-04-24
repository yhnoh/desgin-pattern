package com.example.factorypattern.factory_method_pattern;

import com.example.factorypattern.simple_factory.Pizza;

public class KoreaPizzaStore extends PizzaStore{
    @Override
    public Pizza createPizza(String pizzaType) {
        Pizza pizza = null;

        //타입에 따라서 한국식 피자 생

        return pizza;
    }
}
