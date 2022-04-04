package com.example.factorymethodpattern.after;

public interface ShipFactory {

    default Ship orderShip(String name, String email){
        validate(name, email);
        prepareFor(name);
        Ship ship = createShip();
        sendEmailTo(email, ship);
        return ship;
    }

    Ship createShip();

    default void validate(String name, String email){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("배 이름을 지어주세요.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }

    }

    default void prepareFor(String name) {
        System.out.println(name + " 만들 준비 중");
    }

    default void sendEmailTo(String email, Ship ship) {
        System.out.println(ship.getName() + " 다 만들었습니다.");
    }
}
