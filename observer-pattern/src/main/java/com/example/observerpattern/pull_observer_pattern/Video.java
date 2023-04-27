package com.example.observerpattern.pull_observer_pattern;

public class Video {

    private String subject;
    private String content;

    public Video(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

}
