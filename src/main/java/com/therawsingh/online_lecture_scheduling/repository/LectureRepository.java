package com.therawsingh.online_lecture_scheduling.repository;

import com.therawsingh.online_lecture_scheduling.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {



}
