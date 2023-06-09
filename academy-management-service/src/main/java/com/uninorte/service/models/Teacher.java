package com.uninorte.service.models;

import java.util.List;

import com.uninorte.service.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.TableGenerator;

@Entity
public class Teacher {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "teacher_generator")
	@TableGenerator(
			name = "teacher_generator", table = "id_generator", 
			pkColumnName = "gen_name", valueColumnName = "gen_value", 
			pkColumnValue = "id_teacher", allocationSize = 1)
	private Long id_teacher;
	
	@Column(nullable =  false)
	private String name;
	
	@Column(nullable = false)
	private Integer experience_years;
	
	@Column(nullable = false)
	private String title;
	
	@ManyToMany
    @JoinTable(name = "teach",
            joinColumns = @JoinColumn(name = "id_teacher"),
            inverseJoinColumns = @JoinColumn(name = "id_course"))
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
