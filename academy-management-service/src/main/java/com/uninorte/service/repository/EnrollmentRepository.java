package com.uninorte.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uninorte.service.models.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
	
	@Query(value = "SELECT id_course FROM enrollment WHERE id_student = :student_id AND approved;", nativeQuery=true)
	List<Integer> findAllApprovedCoursesID(String student_id);
}
