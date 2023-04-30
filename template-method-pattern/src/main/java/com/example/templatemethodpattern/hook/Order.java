package com.example.templatemethodpattern.hook;

public abstract class Order {

    private Product product;

    public Order(Product product) {
        this.product = product;
    }

    //할인 로직이 추가된 주문 로직이다.
    public void order(int quantity){
        if(isDiscount()){
            discount1000Won();
        }
        this.prepareOrder(quantity);
        this.completeOrder();
    }

    //할인을 할지 말지 결정하는 로직
    public boolean isDiscount(){
        return false;
    }
    //1000원을 할인해주는 로직
    public void discount1000Won(){
        product.discountPrice(1000);
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
