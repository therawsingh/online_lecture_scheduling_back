package com.therawsingh.online_lecture_scheduling.controller;

import com.therawsingh.online_lecture_scheduling.entity.Course;
import com.therawsingh.online_lecture_scheduling.entity.Instructor;
import com.therawsingh.online_lecture_scheduling.entity.Lecture;
import com.therawsingh.online_lecture_scheduling.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/addCourse")
    public void addCourse(@RequestBody Course course){

        courseRepository.save(course);

    }

}
