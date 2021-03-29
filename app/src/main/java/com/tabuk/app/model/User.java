package com.tabuk.app.model;

public class User {
    private String name;
    private String id;
    private String phone;
    private boolean active = false;
    private String rule = "user";
    private String authId;
    private String faculty;

    public User() {
    }

    public User(String name, String id, String phone, String authId, String faculty) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.authId = authId;
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
