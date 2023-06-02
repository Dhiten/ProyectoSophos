package com.uninorte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uninorte.service.model.*;
import com.uninorte.service.proxy.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins="*")
public class IntegrationController {
	
	@Autowired
	private PersonManagementServiceProxy personManagementServiceProxy;
	
	@Autowired
	private CourseManagementServiceProxy courseManagementServiceProxy;
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student list")
	@Tag(name = "Student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@GetMapping(path= "/integration/student/get/all")
	public List<Student> getAllStudents(){
		return personManagementServiceProxy.getAllStudents();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student")
	@Tag(name = "Student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
			@ApiResponse(responseCode = "404", description = "Student not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path= "/integration/student/get/{student_id}")
	public ResponseEntity<Object> getStudent(@PathVariable int student_id){
		return personManagementServiceProxy.getStudent(student_id);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Register new student")
	@Tag(name = "Student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Student successfully registered.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))) })
	@PostMapping(path= "/integration/student/create")
	public ResponseEntity<Student> createStudent(@RequestBody Student newStudent){
		return personManagementServiceProxy.createStudent(newStudent);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Delete student")
	@Tag(name = "Student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student deleted.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@DeleteMapping(path= "/integration/student/delete")
	public List<Student> deleteStudent(@RequestParam Integer student_id){
		return personManagementServiceProxy.deleteStudent(student_id);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student list that matches the given name")
	@Tag(name = "Student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@GetMapping(path= "/integration/student/get/name/{name}")
	public List<Student> getByName(@PathVariable String name){
		return personManagementServiceProxy.getByName(name);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student list that matches the given faculty")
	@Tag(name = "Student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@GetMapping(path= "/integration/student/get/faculty/{faculty}")
	public List<Student> getByFaculty(@PathVariable String faculty){
		return personManagementServiceProxy.getByFaculty(faculty);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Enroll student")
	@Tag(name = "Student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student enrolled.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
			@ApiResponse(responseCode = "404", description = "Resource not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path= "/integration/student/{student_id}/enroll/{course_id}")
	public ResponseEntity<Object> enrollStudent(@PathVariable int student_id, @PathVariable int course_id){
		return personManagementServiceProxy.enrollStudent(student_id, course_id);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student detail")
	@Tag(name = "Student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDetail.class))),
			@ApiResponse(responseCode = "404", description = "Student not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path= "/integration/student/get/detail/{student_id}")
	public ResponseEntity<Object> getStudentDetail(@PathVariable int student_id){
		return personManagementServiceProxy.getStudentDetail(student_id);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list")
	@Tag(name = "Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/integration/course/get/all")
	public List<Course> getAllCourses(){
		return courseManagementServiceProxy.getAllCourses();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course")
	@Tag(name = "Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Course.class))),
			@ApiResponse(responseCode = "404", description = "Course not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/integration/course/get/{course_id}")
	public ResponseEntity<Object> getCourse(@PathVariable int course_id){
		return courseManagementServiceProxy.getCourse(course_id);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Register new course")
	@Tag(name = "Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Course successfully registered.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Course.class))) })
	@PostMapping(path = "/integration/course/create")
	public ResponseEntity<Course> createCourse(@RequestBody Course newCourse){
		return courseManagementServiceProxy.createCourse(newCourse);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Delete student")
	@Tag(name = "Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course deleted.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@DeleteMapping(path = "/integration/course/delete")
	public List<Course> deleteCourse(@RequestParam Integer course_id){
		return courseManagementServiceProxy.deleteCourse(course_id);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list that matches the given name")
	@Tag(name = "Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/integration/course/get/name/{name}")
	public List<Course> getByName2(@PathVariable String name){
		return courseManagementServiceProxy.getByName(name);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list that have space")
	@Tag(name = "Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/integration/course/get/available/")
	public List<Course> getAvailable(){
		return courseManagementServiceProxy.getAvailable();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list that doesnt have space")
	@Tag(name = "Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/integration/course/get/unavailable/")
	public List<Course> getNotAvailable(){
		return courseManagementServiceProxy.getNotAvailable();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course detailed information")
	@Tag(name = "Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseInfo.class))),
			@ApiResponse(responseCode = "404", description = "Course not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/integration/course/get/detail/{course_id}")
	public ResponseEntity<Object> getAllCoursesWithInfo(@PathVariable Integer course_id){
		return courseManagementServiceProxy.getAllCoursesWithInfo(course_id);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Approve student course")
	@Tag(name = "Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course approved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Enrollment.class))),
			@ApiResponse(responseCode = "404", description = "Enrollment not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/integration/course/approve/{enrollment_id}")
	public ResponseEntity<Object> approveStudent(@PathVariable int enrollment_id){
		return courseManagementServiceProxy.approveStudent(enrollment_id);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Retrieve teacher list")
	@Tag(name = "Teacher")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Teacher list obtained.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class)))
					)
			})
	@GetMapping(path = "/integration/teacher/get/all")
	public List<Teacher> getAllTeachers(){
		return personManagementServiceProxy.getAllTeachers();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve teacher")
	@Tag(name = "Teacher")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Teacher retrieved.", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Teacher.class))
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Teacher not found.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@GetMapping(path = "/integration/teacher/get/{teacher_id}")
	public ResponseEntity<Object> getTeacher(@PathVariable int teacher_id){
		return personManagementServiceProxy.getTeacher(teacher_id);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Register new teacher")
	@Tag(name = "Teacher")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Teacher successfully registered.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Teacher.class))
					)
			})
	@PostMapping(path = "/integration/teacher/create")
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher newTeacher){
		return personManagementServiceProxy.createTeacher(newTeacher);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Delete student")
	@Tag(name = "Teacher")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Teacher deleted.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class)))
					) 
			})
	@DeleteMapping(path = "/integration/teacher/delete")
	public List<Teacher> deleteTeacher(@RequestParam Integer teacher_id){
		return personManagementServiceProxy.deleteTeacher(teacher_id);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Retrieve teacher list that matches the given name")
	@Tag(name = "Teacher")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Teacher list obtained.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class)))
					)
			})
	@GetMapping(path = "/integration/teacher/get/name/{name}")
	public List<Teacher> getByName3(@PathVariable String name){
		return personManagementServiceProxy.getByName3(name);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Assign teacher to course")
	@Tag(name = "Teacher")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Teacher assigned.", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Teacher.class))
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Resource not found.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@GetMapping(path = "/integration/teacher/{teacher_id}/assign/{course_id}")
	public ResponseEntity<Object> enrollStudent2(@PathVariable int teacher_id,@PathVariable int course_id){
		return personManagementServiceProxy.enrollStudent(teacher_id, course_id);
	}
}
