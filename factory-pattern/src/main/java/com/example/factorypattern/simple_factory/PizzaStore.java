package com.example.factorypattern.simple_factory;

public class PizzaStore {

    public Pizza orderPizza(String pizzaType){
        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
        Pizza pizza = simplePizzaFactory.createPizza(pizzaType);

        //피자를 준비하는 과정은 전부 동일하다.
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
