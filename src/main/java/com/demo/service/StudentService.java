package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.dto.StudentCoursesDTO;
import com.demo.entity.Students;
import com.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Page<Students> getByGender(String gender, int page, int size) {

		PageRequest genderPage = PageRequest.of(page, size);

		return studentRepository.findByGender(gender, genderPage);

	}

	public Students saveStudents(Students studnts) {
		return studentRepository.save(studnts);

	}

	public List<Students> saveListOfStudenst(List<Students> listOfStudents) {

		return studentRepository.saveAll(listOfStudents);

	}

	public long getTotalStudents() {
		return studentRepository.count();

	}

	public List<Students> getAllStudents() {
		return studentRepository.findAll();
	}

	public long getCityCount(String city) {
		return studentRepository.countByCity(city);
	}

	public boolean checkStudentExist(int studentId) {
		return studentRepository.existsById(studentId);

	}

	public void deleteStudent(int studentId) {
		studentRepository.deleteById(studentId);
	}

	/* delete based on any column */
	public Students deleteStudentRecords(Students studs) {
		studentRepository.delete(studs);
		return studs;

	}

	public List<StudentCoursesDTO> getAllStudentsWithCourses() {
		return studentRepository.findAllStudentsWithCourse();

	}

}
