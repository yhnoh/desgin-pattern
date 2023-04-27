package com.example.observerpattern.observer_pattern;

public interface Observer {
    //영상 상태 변경을 감지하기위한 메서드
    void update(Video video);
}
