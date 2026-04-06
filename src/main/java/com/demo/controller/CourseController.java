package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Courses;
import com.demo.service.CourseService;

@RestController
@RequestMapping("/api/courses") 
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	
	
	  @GetMapping("/category/{category}")
	    public Page<Courses> getByCategory(
	        @PathVariable String category,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {
	        return courseService.getByCategory(category, page, size);
		
	}
	  
	  @GetMapping("/difficulty/{difficulty}")
	  public Page<Courses> getByDifficulty(@PathVariable String difficulty , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
		return courseService.getByDifficulty(difficulty, page, size);
		  
	  }
	  
	  @GetMapping("/courseName/{courseName}")
	  public Page<Courses> getByCourseName(@PathVariable String courseName , @RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "1") int size){
		return courseService.getByCourseName(courseName, page, size);
		  
	  }
}
