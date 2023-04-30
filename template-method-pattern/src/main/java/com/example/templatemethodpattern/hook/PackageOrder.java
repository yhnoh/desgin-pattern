package com.example.templatemethodpattern.hook;

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
    //할인 가능하도록 재정의
    @Override
    public boolean isDiscount() {
        return true;
    }

}
