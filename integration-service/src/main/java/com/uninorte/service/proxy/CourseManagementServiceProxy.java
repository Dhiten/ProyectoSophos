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


@FeignClient(name = "course-management-service")
public interface CourseManagementServiceProxy {
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/course/get/all")
	public List<Course> getAllCourses();

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Course.class))),
			@ApiResponse(responseCode = "404", description = "Course not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/course/get/{course_id}")
	public ResponseEntity<Object> getCourse(@PathVariable int course_id);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Register new course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Course successfully registered.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Course.class))) })
	@PostMapping(path = "/course/create")
	public ResponseEntity<Course> createCourse(@RequestBody Course newCourse);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Delete student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course deleted.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@DeleteMapping(path = "/course/delete")
	public List<Course> deleteCourse(@RequestParam Integer course_id);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list that matches the given name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/course/get/name/{name}")
	public List<Course> getByName(@PathVariable String name);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list that have space")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/course/get/available/")
	public List<Course> getAvailable();

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course list that doesnt have space")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course list obtained.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })
	@GetMapping(path = "/course/get/unavailable/")
	public List<Course> getNotAvailable();

	@CrossOrigin(origins = "*")
	@Operation(summary = "Retrieve course detailed information")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course retrieved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseInfo.class))),
			@ApiResponse(responseCode = "404", description = "Course not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/course/get/detail/{course_id}")
	public ResponseEntity<Object> getAllCoursesWithInfo(@PathVariable Integer course_id);

	@CrossOrigin(origins = "*")
	@Operation(summary = "Approve student course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Course approved.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Enrollment.class))),
			@ApiResponse(responseCode = "404", description = "Enrollment not found.", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))) })
	@GetMapping(path = "/course/approve/{enrollment_id}")
	public ResponseEntity<Object> approveStudent(@PathVariable int enrollment_id);
}
