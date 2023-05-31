package com.uninorte.service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Course {
	 
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  id_course;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Long  credits;
	
	@Column(nullable = false)
	private Long  spaces;
	
	@ManyToOne
    @JoinColumn(name = "id_prerequisite")
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
