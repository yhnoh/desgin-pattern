package com.example.observerpattern.pull_observer_pattern;

public interface Subject {


    void registerObserver(Observer observer);

    void removerObserver(Observer observer);

    void notifyObserver();
}
