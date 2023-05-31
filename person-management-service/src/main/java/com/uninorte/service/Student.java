package com.uninorte.service;

import java.util.List;

import com.uninorte.service.models.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {
	 
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  id_student;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private int credits;
	
	@Column(nullable=false)
	private String faculty;
	
	@ManyToMany
    @JoinTable(name = "enrollment",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_course"))
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
	
}
