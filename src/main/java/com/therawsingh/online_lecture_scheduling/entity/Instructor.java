package com.therawsingh.online_lecture_scheduling.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Instructor {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "instructor")
    ArrayList<Lecture> lectures;

    public Instructor() {
    }

    public Instructor(String name, ArrayList<Lecture> lectures) {
        this.name = name;
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

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }
}
