package com.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Courses")
@Data
@NoArgsConstructor   // ✅ generates empty constructor
@AllArgsConstructor 
public class Courses {

	    @Id
	    @Column(name = "course_id")      // ✅ maps to DB column exactly
	    private int courseId;            // ✅ fixed typo: cousre → course

	    @Column(name = "course_name")
	    private String courseName;

	    @Column(name = "category")
	    private String category;

	    @Column(name = "instructor")
	    private String instructor;

	    @Column(name = "duration_weeks")
	    private int durationWeeks;

	    @Column(name = "fee")
	    private double fee;

	    @Column(name = "difficulty")
	    private String difficulty;

	    @Column(name = "max_students")
	    private int maxStudents;

	    @Column(name = "start_date")
	    private LocalDate startDate;

		public int getCourseId() {
			return courseId;
		}

		public void setCourseId(int courseId) {
			this.courseId = courseId;
		}

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getInstructor() {
			return instructor;
		}

		public void setInstructor(String instructor) {
			this.instructor = instructor;
		}

		public int getDurationWeeks() {
			return durationWeeks;
		}

		public void setDurationWeeks(int durationWeeks) {
			this.durationWeeks = durationWeeks;
		}

		public double getFee() {
			return fee;
		}

		public void setFee(double fee) {
			this.fee = fee;
		}

		public String getDifficulty() {
			return difficulty;
		}

		public void setDifficulty(String difficulty) {
			this.difficulty = difficulty;
		}

		public int getMaxStudents() {
			return maxStudents;
		}

		public void setMaxStudents(int maxStudents) {
			this.maxStudents = maxStudents;
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
	}
