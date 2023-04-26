package com.quyet.springdatajpatutorial.repository;

import com.quyet.springdatajpatutorial.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Page<Course> findByTitleContaining(String title, Pageable pageable);
}
