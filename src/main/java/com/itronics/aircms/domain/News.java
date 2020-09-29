package com.itronics.aircms.domain;

import java.util.List;

public class News {
    private long newsId;
    private String description;
    private List<String> images;
    private String date;

    public News() {}

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", date='" + date + '\'' +
                '}';
    }
}
