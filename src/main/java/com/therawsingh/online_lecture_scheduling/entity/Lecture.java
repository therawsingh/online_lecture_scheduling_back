package com.therawsingh.online_lecture_scheduling.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Lecture {

    @Id
    @GeneratedValue
    long id;

    String date;

    @ManyToOne(fetch =  FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    Course course;

    @ManyToOne()
    @JoinColumn(name = "instructor_id")
    Instructor instructor;

    public Lecture() {
    }

    public Lecture(String date, Course course, Instructor instructor) {
        this.date = date;
        this.course = course;
        this.instructor = instructor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
