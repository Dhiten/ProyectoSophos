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

import com.uninorte.service.Course;
import com.uninorte.service.CourseInfo;
import com.uninorte.service.repository.*;
import com.uninorte.service.models.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Academy service")
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseRepository repository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	public CourseController() {
		super();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/get/all")
	public List<Course> getAllCourses() {
		return repository.findAll();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Course.class))),
			@ApiResponse(responseCode = "404", description = "Course not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/get/{course_id}")
	public ResponseEntity<Object> getCourse(@PathVariable int course_id) {
		try {
			Course course = repository.findById(course_id).get();
			return ResponseEntity.ok(course);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with asociated id not found.");
		}
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Register new course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Course successfully registered.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Course.class))) })
	@PostMapping(path = "/create")
	public ResponseEntity<Course> createCourse(@RequestBody Course newCourse) {
		Course course = repository.saveAndFlush(newCourse);
		return ResponseEntity.status(HttpStatus.CREATED).body(course);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Delete student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course deleted.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@DeleteMapping(path = "/delete")
	public List<Course> deleteCourse(@RequestParam Integer course_id) {
		repository.deleteById(course_id);
		return repository.findAll();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list that matches the given name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/get/name/{name}")
	public List<Course> getByName(@PathVariable String name) {
		return repository.findByName(name);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list that have space")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/get/available/")
	public List<Course> getAvailable() {
		return repository.findAvailable();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list that doesnt have space")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/get/unavailable/")
	public List<Course> getNotAvailable() {
		return repository.findNotAvailable();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course detailed information")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseInfo.class))),
			@ApiResponse(responseCode = "404", description = "Course not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/get/detail/{course_id}")
	public ResponseEntity<Object> getAllCoursesWithInfo(@PathVariable Integer course_id) {
		try {
			Course course = repository.findById(course_id).get();
			CourseInfo courseInfo = createCourseInfo(course);
			return ResponseEntity.ok(courseInfo);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with asociated id not found.");
		}
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Approve student course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course approved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Enrollment.class))),
			@ApiResponse(responseCode = "404", description = "Enrollment not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/approve/{enrollment_id}")
	public ResponseEntity<Object> approveStudent(@PathVariable int enrollment_id) {
		try {
			Enrollment enrollment = enrollmentRepository.findById(enrollment_id).get();
			enrollment.setApproved(true);
			enrollment = enrollmentRepository.save(enrollment);
			return ResponseEntity.ok(enrollment);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with asociated id not found.");
		}
	}

	private CourseInfo createCourseInfo(Course course) {
		CourseInfo courseInfo = new CourseInfo();
		courseInfo.setId(course.getId_course());
		courseInfo.setName(course.getName());
		courseInfo.setCredits(course.getCredits());
		courseInfo.setSpaces(course.getSpaces());
		courseInfo.setEnrollmentCount(getEnrollmentCount(course));
		courseInfo.setPreRequisite(course.getPrerequisite());
		courseInfo.setTeachers(getTeachersByCourse(course));
		courseInfo.setStudents(getStudentsByCourse(course));
		return courseInfo;
	}

	private int getEnrollmentCount(Course course) {
		List<Student> students = studentRepository.findByCourses(course);
		return students.size();
	}

	private List<Teacher> getTeachersByCourse(Course course) {
		return teacherRepository.findByCourses(course);
	}

	private List<Student> getStudentsByCourse(Course course) {
		return studentRepository.findByCourses(course);
	}

}
