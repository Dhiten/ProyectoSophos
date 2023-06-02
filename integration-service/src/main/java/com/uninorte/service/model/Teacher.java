package com.uninorte.service.model;

import java.util.List;

public class Teacher {
	 
	private Long id_teacher;
	
	private String name;
	
	private Integer experience_years;
	
	private String title;
	
	private List<Course> courses;
	
	public Teacher() {
		
	}

	public Teacher(Long id_teacher, String name, Integer experience_years, String title) {
		super();
		this.id_teacher = id_teacher;
		this.name = name;
		this.experience_years = experience_years;
		this.title = title;
	}

	public Long getId_teacher() {
		return id_teacher;
	}

	public void setId_teacher(Long id_teacher) {
		this.id_teacher = id_teacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getExperience_years() {
		return experience_years;
	}

	public void setExperience_years(Integer experience_years) {
		this.experience_years = experience_years;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
