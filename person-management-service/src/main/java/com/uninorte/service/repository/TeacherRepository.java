package com.uninorte.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uninorte.service.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	
	List<Teacher> findByName(String name);
}
