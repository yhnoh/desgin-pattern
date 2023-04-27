package com.example.observerpattern.pull_observer_pattern;

public class Subscriber1 implements Observer, Notify {

    private Contents contents;
    private Youtube youtube;
    public Subscriber1(Youtube youtube) {
        this.youtube = youtube;
        youtube.registerObserver(this);
    }

    @Override
    public void update() {
        this.contents = youtube.getContents();
    }

    @Override
    public void showNotification() {
    }


}
