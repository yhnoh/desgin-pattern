package com.example.observerpattern.pull_observer_pattern;

public class Subscriber2 implements Observer, Notify {

    private String subject;
    private String content;
    private Youtube youtube;
    public Subscriber2(Youtube youtube) {
        this.youtube = youtube;
        youtube.registerObserver(this);
    }

    @Override
    public void update() {
        Video video = youtube.getVideo();
        this.subject = video.getSubject();
        this.content = video.getContent();
        this.sendNotification();
    }

    @Override
    public void sendNotification() {
        System.out.println("구독자2에게 업로드된 영상 알람을 보냈습니다.");
        System.out.println("영상 제목 = " + subject  + ", 영상 내용 = " + content);
    }


}
