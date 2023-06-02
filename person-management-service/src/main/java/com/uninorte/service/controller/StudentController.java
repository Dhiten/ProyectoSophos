package com.uninorte.service.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uninorte.service.Student;
import com.uninorte.service.StudentDetail;
import com.uninorte.service.models.Course;
import com.uninorte.service.models.Enrollment;
import com.uninorte.service.repository.CourseRepository;
import com.uninorte.service.repository.EnrollmentRepository;
import com.uninorte.service.repository.StudentRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Student service")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	public StudentController() {
		super();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student list")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@GetMapping(path = "/get/all")
	public List<Student> getAllStudents() {
		return repository.findAll();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
			@ApiResponse(responseCode = "404", description = "Student not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/get/{student_id}")
	public ResponseEntity<Object> getStudent(@PathVariable int student_id) {
		try {
			Student student = repository.findById(student_id).get();
			return ResponseEntity.ok(student);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with asociated id not found.");
		}
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Register new student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Student successfully registered.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))) })
	@PostMapping(path = "/create")
	public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) {
		Student student = repository.saveAndFlush(newStudent);
		return ResponseEntity.status(HttpStatus.CREATED).body(student);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Delete student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student deleted.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@DeleteMapping(path = "/delete")
	public List<Student> deleteStudent(@RequestParam Integer student_id) {
		repository.deleteById(student_id);
		return repository.findAll();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student list that matches the given name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@GetMapping(path = "/get/name/{name}")
	public List<Student> getByName(@PathVariable String name) {
		return repository.findByName(name);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student list that matches the given faculty")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@GetMapping(path = "/get/faculty/{faculty}")
	public List<Student> getByFaculty(@PathVariable String faculty) {
		return repository.findByName(faculty);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Enroll student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student enrolled.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
			@ApiResponse(responseCode = "404", description = "Resource not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/{student_id}/enroll/{course_id}")
	public ResponseEntity<Object> enrollStudent(@PathVariable int student_id, @PathVariable int course_id) {
		try {
			Student student = repository.findById(student_id).get();
			Course course = courseRepository.findById(course_id).get();
			Enrollment studentEnrollment = new Enrollment();
			studentEnrollment.setId_course(course);
			studentEnrollment.setId_student(student);
			studentEnrollment.setApproved(false);
			enrollmentRepository.save(studentEnrollment);
			return ResponseEntity.ok(student);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with asociated id not found.");
		}
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student detail")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDetail.class))),
			@ApiResponse(responseCode = "404", description = "Student not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/get/detail/{student_id}")
	public ResponseEntity<Object> getStudentDetail(@PathVariable int student_id) {
		try {
			Student student = repository.findById(student_id).get();
			List<Long> ides = enrollmentRepository.findAllApprovedCoursesID(student_id);
			StudentDetail d = new StudentDetail(student, ides);
			return ResponseEntity.ok(d);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with asociated id not found.");
		}
	}
}
