package com.uninorte.service.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.uninorte.service.model.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@FeignClient(name = "person-management-service")
public interface PersonManagementServiceProxy {
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student list")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@GetMapping(path= "/student/get/all")
	public List<Student> getAllStudents();

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
			@ApiResponse(responseCode = "404", description = "Student not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path= "/student/get/{student_id}")
	public ResponseEntity<Object> getStudent(@PathVariable int student_id);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Register new student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Student successfully registered.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))) })
	@PostMapping(path= "/student/create")
	public ResponseEntity<Student> createStudent(@RequestBody Student newStudent);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Delete student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student deleted.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@DeleteMapping(path= "/student/delete")
	public List<Student> deleteStudent(@RequestParam Integer student_id);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student list that matches the given name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@GetMapping(path= "/student/get/name/{name}")
	public List<Student> getByName(@PathVariable String name);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student list that matches the given faculty")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class)))) })
	@GetMapping(path= "/student/get/faculty/{faculty}")
	public List<Student> getByFaculty(@PathVariable String faculty);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Enroll student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student enrolled.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
			@ApiResponse(responseCode = "404", description = "Resource not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path= "/student/{student_id}/enroll/{course_id}")
	public ResponseEntity<Object> enrollStudent(@PathVariable int student_id, @PathVariable int course_id);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve student detail")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDetail.class))),
			@ApiResponse(responseCode = "404", description = "Student not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path= "/student/get/detail/{student_id}")
	public ResponseEntity<Object> getStudentDetail(@PathVariable int student_id);
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Retrieve teacher list")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Teacher list obtained.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class)))
					)
			})
	@GetMapping(path = "/teacher/get/all")
	public List<Teacher> getAllTeachers();
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve teacher")
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
	@GetMapping(path = "/teacher/get/{teacher_id}")
	public ResponseEntity<Object> getTeacher(@PathVariable int teacher_id);
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Register new teacher")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Teacher successfully registered.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Teacher.class))
					)
			})
	@PostMapping(path = "/teacher/create")
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher newTeacher);
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Delete student")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Teacher deleted.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class)))
					) 
			})
	@DeleteMapping(path = "/teacher/delete")
	public List<Teacher> deleteTeacher(@RequestParam Integer teacher_id);
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Retrieve teacher list that matches the given name")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Teacher list obtained.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class)))
					)
			})
	@GetMapping(path = "/teacher/get/name/{name}")
	public List<Teacher> getByName3(@PathVariable String name);
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Assign teacher to course")
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
	@GetMapping(path = "/teacher/{teacher_id}/assign/{course_id}")
	public ResponseEntity<Object> enrollStudent2(@PathVariable int teacher_id,@PathVariable int course_id);
}
