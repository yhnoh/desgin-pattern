package com.example.observerpattern.observer_pattern;

public class Subscriber1 implements Observer, Notify {

    private String subject = "";
    private String content = "";
    public Subscriber1(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void update(Video video) {
        this.subject = video.getSubject();
        this.content = video.getContent();
        this.showNotification();
    }
    //알람 받기
    @Override
    public void showNotification() {
        System.out.println("구독자1에게 업로드된 영상 알람을 보냈습니다.");
        System.out.println("영상 제목 = " + subject  + ", 영상 내용 = " + content);

    }
}
