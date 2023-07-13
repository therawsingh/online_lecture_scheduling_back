package com.therawsingh.online_lecture_scheduling.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Course {

    @Id
    @GeneratedValue
    long id;

    String name;

    int level;

    String description;

    @OneToMany(mappedBy = "course")
    ArrayList<Lecture> lectures;

    public Course() {
    }

    public Course(String name, int level, String description, ArrayList<Lecture> lectures) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.lectures = lectures;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }
}
