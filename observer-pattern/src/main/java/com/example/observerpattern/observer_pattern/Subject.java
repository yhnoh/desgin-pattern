package com.example.observerpattern.observer_pattern;

public interface Subject {

    //옵저버 등록
    void registerObserver(Observer observer);

    //옵저버 삭제
    void removerObserver(Observer observer);

    //옵저버들에게 알림
    void notifyObserver();
}
