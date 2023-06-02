package com.uninorte.service.model;

import java.util.List;

public class Student {
	
	private Long  id_student;
	
	private String name;
	
	private int credits;
	
	private String faculty;
	
	private int semester;
	
	private List<Course> courses;
	
	public Student() {
		
	}

	public Student(Long id_student, String name, int credits, String faculty) {
		super();
		this.id_student = id_student;
		this.name = name;
		this.credits = credits;
		this.faculty = faculty;
	}

	public Long getId_student() {
		return id_student;
	}

	public void setId_student(Long id_student) {
		this.id_student = id_student;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
	
}
