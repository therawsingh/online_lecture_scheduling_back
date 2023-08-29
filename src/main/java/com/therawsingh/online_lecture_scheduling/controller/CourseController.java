package com.therawsingh.online_lecture_scheduling.controller;

import com.therawsingh.online_lecture_scheduling.entity.Course;
import com.therawsingh.online_lecture_scheduling.entity.Instructor;
import com.therawsingh.online_lecture_scheduling.entity.Lecture;
import com.therawsingh.online_lecture_scheduling.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("/course")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/addCourse")
    public String addCourse(@RequestBody Course course){

        courseRepository.save(course);

        return "Course successfully added";

    }

}
