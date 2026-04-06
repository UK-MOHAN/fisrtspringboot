package com.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.dto.StudentCoursesDTO;
import com.demo.entity.Students;

@Repository
public interface StudentRepository extends JpaRepository<Students , Integer>{
	
	Page<Students> findByGender(String gender , PageRequest genderPage);
	
	long countByCity(String city);
	
	
	@Query("SELECT new com.demo.dto.StudentCoursesDTO(" +
		       "s.firstName, s.lastName, " +
		       "c.courseName, s.email, " +
		       "c.category, sc.completionStatus, " +
		       "sc.score, c.fee) " +
		       "FROM StudentCourses sc " +
		       "JOIN sc.student s " +
		       "JOIN sc.course c")
		List<StudentCoursesDTO> findAllStudentsWithCourse();
	

}
