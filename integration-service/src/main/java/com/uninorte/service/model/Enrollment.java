package com.uninorte.service.model;


public class Enrollment {

	private Long  id_enrollment;
	
    private Student id_student;

    private Course id_course;

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
