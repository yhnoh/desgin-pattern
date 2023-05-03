package com.example.proxypattern.jdk_dynamic_proxy;

import com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.*;
import com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.member.Member;
import com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.member.MemberRepository;
import com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.order.Order;
import com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.order.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProxyTest {

    @Test
    void memberRepositoryProxyTest(){
        Repository memberRepository = new MemberRepository();

        RepositoryInvocationHandler handler = new RepositoryInvocationHandler(memberRepository);

        Repository<Member, Long> memberRepositoryProxy = (Repository) Proxy.newProxyInstance(memberRepository.getClass().getClassLoader(),
                memberRepository.getClass().getInterfaces(),
                handler);

        List<Member> members = memberRepositoryProxy.findAll();

        assertThat(members.size()).isEqualTo(3);
        assertThat(members).extracting(Member::getId, Member::getUsername)
                .containsExactly(tuple(1L, "username1"),
                        tuple(2L, "username2"),
                        tuple(3L, "username3"));


    }

    @Test
    void orderRepositoryProxyTest(){
        Repository orderRepository = new OrderRepository();
        RepositoryInvocationHandler handler = new RepositoryInvocationHandler(orderRepository);

        Repository<Order, Long> orderRepositoryProxy = (Repository) Proxy.newProxyInstance(orderRepository.getClass().getClassLoader(),
                orderRepository.getClass().getInterfaces(),
                handler);

        List<Order> orders = orderRepositoryProxy.findAll();

        assertThat(orders.size()).isEqualTo(3);
        assertThat(orders).extracting(Order::getId, Order::getOrderProductName, Order::getOrderQuantity)
                .containsExactly(tuple(1L, "orderProductName1", 1),
                        tuple(2L, "orderProductName2", 2),
                        tuple(3L, "orderProductName3", 3));

    }
}
