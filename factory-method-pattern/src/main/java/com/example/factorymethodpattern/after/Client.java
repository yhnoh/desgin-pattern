package com.example.factorymethodpattern.after;


/**
 * OCP 원칙을 지키고 있냐? 확장에 열려 있고, 변경에 닫혀 있느냐?
 * 팩토리가 추가되었을때, 코드 수정을 하지 않느냐??
 * 기존 코드를 수정하지 않는다. 새로운 기능을 얼마든지 확장이 가능하다.
 *
 * 블랙쉽 공정을 추가할 때 화이트쉽 코드를 다시 고칠 필요가 있느냐??
 * - 클라이언트 코드가 수정되어있지 않느냐?
 * - 의존성 주입을 활용하여 사용 가능
 *
 * 단점
 * - 팩토리 패턴 적용시, 각자의 역할을 나누다 보니 클래스가 많아진다.
 *
 * 자바8에서 인터페이스에서 defalut를 통해서 메소드 바디를 설정이 가능하다.
 * 자바9에서는 인터페이스에서 private 접근 제어자를 구현하였기 때문에, 추상메소드를 구현하지 않아도 된다.
 *
 * 실제사용처
 * - BeanFactory에서 사용을 하고 있다.
 * BeanFactory는 Factory 인터페이스
 * BeanFactory의 구현체는  Factory의 구현체 (ClassPathXmlApplcationContext, AnnotationConfigApplicationContext
 * BeanFactory.getBean을 이용하여 각 Product를 구현 (Object)
 * BeanFactory.getBean의 인자를 이용하여 Product구현체를 가져온다.? or 생성한다.
 */

public class Client {

    public static void main(String[] args) {
//        Ship whiteship = new WhiteShipFactory().orderShip("Whiteship", "keesun@mail.com");
//        System.out.println(whiteship);
//
//        Ship blackship = new BlackShipFactory().orderShip("Blackship", "keesun@mail.com");
//        System.out.println(blackship);

        Client client = new Client();
        client.print(new WhiteShipFactory(), "Whiteship", "keesun@mail.com");
        client.print(new WhiteShipFactory(), "Blackship", "keesun@mail.com");
    }

    private void print(ShipFactory shipFactory, String name, String email){
        System.out.println(shipFactory.orderShip(name, email));

    }

}
