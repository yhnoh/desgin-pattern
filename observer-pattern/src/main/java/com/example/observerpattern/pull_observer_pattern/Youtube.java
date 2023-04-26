package com.example.observerpattern.pull_observer_pattern;

import java.util.ArrayList;
import java.util.List;

public class Youtube implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private Video video;
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void uploadVideo(Video video){
        this.video = video;
        this.notifyObserver();
    }

    public Video getVideo() {
        return video;
    }
}
