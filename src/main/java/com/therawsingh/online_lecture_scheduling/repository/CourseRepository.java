package com.therawsingh.online_lecture_scheduling.repository;

import com.therawsingh.online_lecture_scheduling.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByName(String name);

}
