package com.example.observerpattern.pull_observer_pattern;

import java.util.ArrayList;
import java.util.List;

public class Youtube implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private Contents contents;
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    //옵저버에게 알려줄 때, 더이상 객체 정보를 넘겨주지 않아도 된다.
    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void uploadContents(Contents contents){
        this.contents = contents;
        this.notifyObserver();
    }

    //옵저버 구현 클래스에서 해당 데이터 정보를 가져갈 수 있도록 Getter 메서드 만들기
    public Contents getContents() {
        return contents;
    }
}
