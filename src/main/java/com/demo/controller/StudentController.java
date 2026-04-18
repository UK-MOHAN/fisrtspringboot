package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.StudentCoursesDTO;
import com.demo.entity.Students;
import com.demo.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// Get only by gender
	@GetMapping("/gender/{gender}")
	public Page<Students> getByCourseName(@PathVariable String gender, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "1") int size) {
		return studentService.getByGender(gender, page, size);

	}

	// SAVE ONLY ONE OBJECT
	@PostMapping
	public ResponseEntity<Students> saveStudents(@RequestBody Students studnts) {
		// return
		Students stud = studentService.saveStudents(studnts);

		return ResponseEntity.ok(stud);

	}

	// SAVE LIST OF OBJECTS
	@PostMapping("/listOfstudents")
	public ResponseEntity<List<Students>> saveALLStudents(@RequestBody List<Students> lisOfStudents) {

		List<Students> lisStud = studentService.saveListOfStudenst(lisOfStudents);
		return ResponseEntity.ok(lisStud);

	}

	// GET ALL RECORDS
	@GetMapping
	public ResponseEntity<List<Students>> getAllStudentsData() {

		return ResponseEntity.ok(studentService.getAllStudents());

	}

	// TotalRecordsCount
	@GetMapping("totalCount")
	public ResponseEntity<Long> getCount() {
		return ResponseEntity.ok(studentService.getTotalStudents());
	}

	// COUNT NO OF COUNTRIES
	@GetMapping("city/{city}")
	public ResponseEntity<Long> getCitiesCount(@PathVariable String city) {
		return ResponseEntity.ok(studentService.getCityCount(city));

	}

	// CHECK STUDENT EXISTS
	@GetMapping("check/{studentId}")
	public ResponseEntity<Boolean> checkStudentExists(@PathVariable int studentId) {
		return ResponseEntity.ok(studentService.checkStudentExist(studentId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.ok("Student deleted SucessFully");

	}

	@DeleteMapping("deleteRecord")
	public ResponseEntity<Students> deleteByStudent(@RequestBody Students studs) {
		Students stud22 = studentService.deleteStudentRecords(studs);
		return ResponseEntity.ok(stud22);

	}

	// All students with courses
	@GetMapping("/with-courses")
	public ResponseEntity<List<StudentCoursesDTO>> getAllStudentsAndCourses() {
		return ResponseEntity.ok(studentService.getAllStudentsWithCourses());

	}

}
