package com.uninorte.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uninorte.service.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	List<Course> findByName(String name);
	
	@Query(value="SELECT * FROM Course WHERE spaces > 0",nativeQuery=true)
	List<Course> findAvailable();
	
	@Query(value="SELECT * FROM Course WHERE spaces = 0",nativeQuery=true)
	List<Course> findNotAvailable();
}
