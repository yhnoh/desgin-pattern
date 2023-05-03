package com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.order;

import com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderRepository implements Repository<Order, Long> {

    private final Map<Long, Order> orders = new HashMap<>();

    public OrderRepository() {
        orders.put(1L, new Order(1L, "orderProductName1", 1));
        orders.put(2L, new Order(2L, "orderProductName2", 2));
        orders.put(3L, new Order(3L, "orderProductName3", 3));
    }

    @Override
    public Order findById(Long id) {
        return orders.get(id);
    }

    @Override
    public List<Order> findAll() {
        return orders.values().stream().collect(Collectors.toList());
    }
}
