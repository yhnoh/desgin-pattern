package com.example.factorypattern.simple_factory;

public class SimplePizzaFactory {

    public Pizza createPizza(String pizzaType) {
        Pizza pizza = null;

        if(pizzaType.equals("cheese")){
            pizza = new ChessePizza();
        }else if(pizzaType.equals("pepperoni")){
            pizza = new PepperoniPizza();
        }

        return pizza;
    }

}
