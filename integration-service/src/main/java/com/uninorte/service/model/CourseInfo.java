package com.uninorte.service.model;

import java.util.List;


public class CourseInfo {

    private Long id;
    private String name;
    private Long credits;
    private Long spaces;
    private int enrollmentCount;
    private Course preRequisite;
    private List<Teacher> teachers;
    private List<Student> students;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCredits() {
		return credits;
	}
	public void setCredits(Long credits) {
		this.credits = credits;
	}
	public Long getSpaces() {
		return spaces;
	}
	public void setSpaces(Long spaces) {
		this.spaces = spaces;
	}
	public int getEnrollmentCount() {
		return enrollmentCount;
	}
	public void setEnrollmentCount(int enrollmentCount) {
		this.enrollmentCount = enrollmentCount;
	}
	public Course getPreRequisite() {
		return preRequisite;
	}
	public void setPreRequisite(Course preRequisite) {
		this.preRequisite = preRequisite;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

    
}