package com.uninorte.service.models;

import com.uninorte.service.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enrollment {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  id_enrollment;
	
    @ManyToOne
    @JoinColumn(name = "id_student",nullable=false)
    private Student id_student;

    @ManyToOne
    @JoinColumn(name = "id_course",nullable=false)
    private Course id_course;

    @Column(nullable=false, columnDefinition = "boolean default false")
    private boolean approved;
    
    public Enrollment() {
    	
    }

	public Enrollment(Long id_enrollment, Student id_student, Course id_course, boolean approved) {
		super();
		this.id_enrollment = id_enrollment;
		this.id_student = id_student;
		this.id_course = id_course;
		this.approved = approved;
	}

	public Long getId_enrollment() {
		return id_enrollment;
	}

	public void setId_enrollment(Long id_enrollment) {
		this.id_enrollment = id_enrollment;
	}

	public Student getId_student() {
		return id_student;
	}

	public void setId_student(Student id_student) {
		this.id_student = id_student;
	}

	public Course getId_course() {
		return id_course;
	}

	public void setId_course(Course id_course) {
		this.id_course = id_course;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
    
}
