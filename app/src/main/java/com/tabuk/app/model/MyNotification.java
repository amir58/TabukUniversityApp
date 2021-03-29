package com.tabuk.app.model;

public class MyNotification {

    private String content;
    private String faculty;
    private String level;

    public MyNotification(String content, String faculty, String level) {
        this.content = content;
        this.faculty = faculty;
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
