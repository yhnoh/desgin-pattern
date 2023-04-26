package com.example.observerpattern.observer_pattern;

import org.junit.jupiter.api.Test;

public class ObserverPatternTest {

    @Test
    void observerPatternTest(){

        Youtube youtube = new Youtube();
        //옵저버 등록
        Observer subscriber1 = new Subscriber1(youtube);
        Observer subscriber2 = new Subscriber2(youtube);

        //영상을 업로드하여 구독자들에게 알림
        Video video1 = new Video("제목1", "내용1");
        youtube.uploadVideo(video1);

        //구독자1 구독 해지
        youtube.removerObserver(subscriber1);
        System.out.println("================================");

        //영상을 업로드하여 구독자들에게 알림
        Video video2 = new Video("제목2", "내용2");
        youtube.uploadVideo(video2);
    }
}
