package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.entity.Courses;
import com.demo.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public Page<Courses> getByCategory(String category, int page, int size) {

		PageRequest pageable = PageRequest.of(page, size);
		return courseRepository.findByCategory(category, pageable);

	}

	public Page<Courses> getByDifficulty(String difficulty, int page, int size) {

		PageRequest difficultPage = PageRequest.of(page, size);
//mohan
		return courseRepository.findByDifficulty(difficulty, difficultPage);

	}

	public Page<Courses> getByCourseName(String courseName, int page, int size) {
		PageRequest courseName1 = PageRequest.of(page, size);

		return courseRepository.findByCourseName(courseName, courseName1);

	}

}
