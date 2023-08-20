package com.therawsingh.online_lecture_scheduling.repository;

import com.therawsingh.online_lecture_scheduling.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByName(String name);

}
