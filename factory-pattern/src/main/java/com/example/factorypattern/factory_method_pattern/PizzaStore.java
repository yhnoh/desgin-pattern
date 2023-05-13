package com.example.factorypattern.factory_method_pattern;


public abstract class PizzaStore {

    public Pizza orderPizza(String pizzaType){
        Pizza pizza = this.createPizza(pizzaType);
        //피자를 준비하는 과정은 전부 동일하다.
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    //생성 관련 책임을 추상 메서드에게 전가
    public abstract Pizza createPizza(String pizzaType);
}
