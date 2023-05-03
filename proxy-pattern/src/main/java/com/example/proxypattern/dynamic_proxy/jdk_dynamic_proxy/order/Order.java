package com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.order;

public class Order {

    private Long id;
    private String orderProductName;
    private int orderQuantity;

    public Order(Long id, String orderProductName, int orderQuantity) {
        this.id = id;
        this.orderProductName = orderProductName;
        this.orderQuantity = orderQuantity;
    }

    public Long getId() {
        return id;
    }

    public String getOrderProductName() {
        return orderProductName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }
}
