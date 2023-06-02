package com.uninorte.service.model;

public class Course {

	
	private Long id_course;

	private String name;

	private Long credits;

	private Long spaces;

	private Course pre_requisite;

	public Course() {

	}

	public Course(Long id_course, String name, Long credits, Long spaces, Course pre_requisite) {
		super();
		this.id_course = id_course;
		this.name = name;
		this.credits = credits;
		this.spaces = spaces;
		this.pre_requisite = pre_requisite;
	}

	public Long getId_course() {
		return id_course;
	}

	public void setId_course(Long id_course) {
		this.id_course = id_course;
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

	public Course getPrerequisite() {
		return pre_requisite;
	}

	public void setPrerequisite(Course pre_requisite) {
		this.pre_requisite = pre_requisite;
	}

}
