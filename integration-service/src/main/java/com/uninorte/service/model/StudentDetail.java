package com.uninorte.service.model;

import java.util.List;
import java.util.ArrayList;

public class StudentDetail {
	
	private Long  id_student;
	
	private String name;
	
	private int credits;
	
	private String faculty;
	
	private int semester;
	
	private List<Course> approvedCourses;
	
	private List<Course> currentCourses;
	
	public StudentDetail(Student s, List<Long> ides) {
		this.id_student = s.getId_student();
		this.name = s.getName();
		this.credits = s.getCredits();
		this.faculty = s.getFaculty();
		this.semester = s.getSemester();
		this.approvedCourses = new ArrayList<>();
		this.currentCourses = new ArrayList<>();
		List<Course> cursos = s.getCourses();
		for(Course c : cursos) {
			if(ides != null & ides.contains(c.getId_course())) {
				this.approvedCourses.add(c);
			}else {
				this.currentCourses.add(c);
			}
		}
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

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public List<Course> getApprovedCourses() {
		return approvedCourses;
	}

	public void setApprovedCourses(List<Course> approvedCourses) {
		this.approvedCourses = approvedCourses;
	}

	public List<Course> getCurrentCourses() {
		return currentCourses;
	}

	public void setCurrentCourses(List<Course> currentCourses) {
		this.currentCourses = currentCourses;
	}
	
}
