package com.example.factorypattern.abstract_factory_pattern;

//피자 생성 관련 인터페이스
public interface PizzaFactory {

    Pizza createPizza(String pizzaType);
}
