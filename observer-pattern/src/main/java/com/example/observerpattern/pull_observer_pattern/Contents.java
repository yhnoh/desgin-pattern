package com.example.observerpattern.pull_observer_pattern;

public class Contents {
    private Video video;
    private News news;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
