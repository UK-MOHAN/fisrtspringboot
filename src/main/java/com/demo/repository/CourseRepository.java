package com.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {

	Page<Courses> findByCategory(String category, PageRequest pageable);

	Page<Courses> findByDifficulty(String difficulty, PageRequest pageable);

	Page<Courses> findByCourseName(String courseName, PageRequest pageable);

}
