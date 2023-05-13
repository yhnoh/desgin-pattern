package com.example.factorypattern.abstract_factory_pattern;


public abstract class PizzaStore {
    //팩토리 객체를 구성
    private final PizzaFactory pizzaFactory;
    public PizzaStore(PizzaFactory pizzaFactory) {
        this.pizzaFactory = pizzaFactory;
    }

    public Pizza orderPizza(String pizzaType){
        Pizza pizza = pizzaFactory.createPizza(pizzaType);
        //피자를 준비하는 과정은 전부 동일하다.
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
