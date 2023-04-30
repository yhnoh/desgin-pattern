package com.example.templatemethodpattern.template_method_pattern;

public class Product {

    private String name = "";
    private double price = 0;
    private int quantity = 0;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void minusQuantity(int quantity){
        if(this.quantity < quantity){
            throw new IllegalArgumentException("상품 개수가 0보다 작을 수 없습니다. 현재 개수 = " + quantity);
        }

        this.quantity -= quantity;
    }

    public double totalPrice(int quantity){
        return price * quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
