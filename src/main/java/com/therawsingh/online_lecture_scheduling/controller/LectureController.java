package com.therawsingh.online_lecture_scheduling.controller;

import com.therawsingh.online_lecture_scheduling.entity.Course;
import com.therawsingh.online_lecture_scheduling.entity.Instructor;
import com.therawsingh.online_lecture_scheduling.entity.Lecture;
import com.therawsingh.online_lecture_scheduling.repository.CourseRepository;
import com.therawsingh.online_lecture_scheduling.repository.InstructorRepository;
import com.therawsingh.online_lecture_scheduling.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/lecture")
@CrossOrigin
public class LectureController {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LectureRepository lectureRepository;


    //Make this ResponseEntity
    @PostMapping("/assignLecture/{instructor_name}/{course_name}")
    public void assignLecture(@RequestBody Lecture lecture, @PathVariable String instructor_name, @PathVariable String course_name) {

        Instructor instructor = instructorRepository.findByName(instructor_name);
        Course course = courseRepository.findByName(course_name);

        boolean dateMismatch = false;
        for (Lecture lect : instructor.getLectures()) {
            if(lect.getDate().equals(lecture.getDate())){
                dateMismatch = true;
                break;
            }
        }

        if (!dateMismatch) {
            lecture.setInstructor(instructor);
            lecture.setCourse(course);

            lectureRepository.save(lecture);
        }
    }

    @GetMapping("/getLectures/{instructor_name}")
    public ArrayList<Lecture> getLectures(@PathVariable String instructor_name) {

        ArrayList<Lecture> lectures = new ArrayList<>();

        return lectures;

    }

}
