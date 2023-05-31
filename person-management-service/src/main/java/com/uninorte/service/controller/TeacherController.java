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

import com.uninorte.service.Teacher;
import com.uninorte.service.repository.TeacherRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Teacher service")
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherRepository repository;
	
	
	public TeacherController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Retrieve teacher list")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Teacher list obtained.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class)))
					)
			})
	@GetMapping(path = "/get/all")
	public List<Teacher> getAllTeachers(){
		return repository.findAll();
	}
	
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
	@GetMapping(path = "/get/{teacher_id}")
	public ResponseEntity<Object> getTeacher(@PathVariable int teacher_id){		
		try {
			Teacher teacher = repository.findById(teacher_id).get();
			return ResponseEntity.ok(teacher);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher with asociated id not found.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Register new teacher")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Teacher successfully registered.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Teacher.class))
					)
			})
	@PostMapping(path = "/create")
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher newTeacher){
		Teacher teacher = repository.saveAndFlush(newTeacher);
		return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Delete student")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Teacher deleted.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class)))
					) 
			})
	@DeleteMapping(path = "/delete")
	public List<Teacher> deleteTeacher(@RequestParam Integer teacher_id){
		repository.deleteById(teacher_id);
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Retrieve teacher list that matches the given name")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Teacher list obtained.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class)))
					)
			})
	@GetMapping(path = "/get/name/{name}")
	public List<Teacher> getByName(@PathVariable String name){
		return repository.findByName(name);
	}

}
