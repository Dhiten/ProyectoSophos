package com.uninorte.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uninorte.service.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	List<Student> findByName(String name);
	
	List<Student> findByFaculty(String faculty);
}
