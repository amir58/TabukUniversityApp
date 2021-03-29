package com.tabuk.app.model;

public class MyLecture {

    private String id;
    private String name;
    private String day;
    private String time;
    private String faculty;
    private String level;

    public MyLecture(String id, String name, String day, String time, String faculty, String level) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.time = time;
        this.faculty = faculty;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
