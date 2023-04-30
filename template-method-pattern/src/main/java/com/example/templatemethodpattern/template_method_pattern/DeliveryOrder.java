package com.example.templatemethodpattern.template_method_pattern;

public class DeliveryOrder extends Order{

    private Product product;
    public DeliveryOrder(Product product) {
        super(product);
        this.product = product;
    }

    //배달 주문 정의
    @Override
    public void completeOrder() {
        System.out.println(product.getName() + " " + product.getQuantity() + "개 배달되었습니다.");

    }
}
