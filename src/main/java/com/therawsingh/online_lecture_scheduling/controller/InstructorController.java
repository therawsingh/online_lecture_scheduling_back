package com.therawsingh.online_lecture_scheduling.controller;

import com.therawsingh.online_lecture_scheduling.entity.Instructor;
import com.therawsingh.online_lecture_scheduling.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InstructorController {

    @Autowired
    InstructorRepository instructorRepository;

    @GetMapping("/getInstructors")
    public List<Instructor> getInstructors(){

        List<Instructor> instructors = instructorRepository.findAll();
        return instructors;

    }

    @PostMapping("/addInstructor")
    public String addInstructor(@RequestBody Instructor instructor){

        instructorRepository.save(instructor);

        return "Instructor addedd successfully";

    }

}
