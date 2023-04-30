package com.example.templatemethodpattern.template_method_pattern;

public abstract class Order {

    private Product product;

    public Order(Product product) {
        this.product = product;
    }

    //주문을 하는 로직이다.
    //주문시 필요한 주문 준비와, 주문 완료에 대한 메서드로 구성되어 있다.
    public void order(int quantity){
        this.prepareOrder(quantity);
        this.completeOrder();
    }

    public void prepareOrder(int quantity){
        product.minusQuantity(quantity);

        System.out.println(product.getName() + "을 " + product.getQuantity() + "개 주문하였습니다.");
        System.out.println("총 " + product.totalPrice(quantity) + "원 입니다.");
        System.out.println(product.getName() + "의 남은 수량은 " + product.getQuantity() + "개 입니다.");
    }

    // 서브 클래스에서 정의한 방식대로 주문 준비가 완료된다.
    public abstract void completeOrder();

}
