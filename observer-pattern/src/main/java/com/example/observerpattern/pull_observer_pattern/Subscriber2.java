package com.example.observerpattern.pull_observer_pattern;

public class Subscriber2 implements Observer, Notify {
    private Contents contents;
    private Youtube youtube;
    public Subscriber2(Youtube youtube) {
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
