package com.tabuk.app.model;

public class User {
    private String name;
    private String id;
    private String phone;
    private boolean active = true;
    private String rule = "user";

    public User(String name, String id, String phone) {
        this.name = name;
        this.id = id;
        this.phone = phone;
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
