package com.zeeshanabid.jblog.model;

import java.time.LocalDateTime;

public class Post {
    private int    id;
    private String title;
    private String content;
    private LocalDateTime time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                    .append(id)
                    .append(": ")
                    .append(title)
                    .toString();
    }
}
