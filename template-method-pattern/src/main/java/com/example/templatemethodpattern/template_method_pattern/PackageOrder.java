package com.example.templatemethodpattern.template_method_pattern;

public class PackageOrder extends Order {

    private Product product;
    public PackageOrder(Product product) {
        super(product);
        this.product = product;
    }

    //포장 주문 정의
    @Override
    public void completeOrder() {
        System.out.println(product.getName() + "이 " + product.getQuantity() + "개 포장되었습니.");
    }
}
