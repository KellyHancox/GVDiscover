package com.example.gvdiscoverapp;

import java.text.SimpleDateFormat;

public class GVEvent {
    private String name;
    private String category;
    private String startDate;
    private String endDate;

    private String startTime;
    private String endTime;
    private String description;

    public GVEvent(String inputName, String inputCategory, String inputDescription) {
        name = inputName;
        category = inputCategory;
        description = inputDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setStart(int hour, int minute, char timeOfDay, int month, int day, int year){
        startTime = hour + ":" + minute + timeOfDay;
        startDate = month + "/" + day + "/" + year;
    }

    public void setEnd(int hour, int minute, char timeOfDay, int month, int day, int year) {
        endTime = hour + ":" + minute + timeOfDay;
        endDate = month + "/" + day + "/" + year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


