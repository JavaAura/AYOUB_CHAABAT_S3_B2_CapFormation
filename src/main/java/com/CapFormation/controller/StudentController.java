package com.capFormation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capFormation.model.Student;
import com.capFormation.service.interfaces.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/students")
@Api(value = "Student Management System", description = "Operations pertaining to students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "View a list of available students", response = Page.class)
    @GetMapping
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentService.findAll(pageable);
    }

    @ApiOperation(value = "Get a student by Id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved student"),
        @ApiResponse(code = 404, message = "Student not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Add a new student")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created student"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @ApiOperation(value = "Update an existing student")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated student"),
        @ApiResponse(code = 404, message = "Student not found"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.findById(id)
            .map(existingStudent -> {
                student.setId(id);
                return ResponseEntity.ok(studentService.save(student));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Delete a student")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted student"),
        @ApiResponse(code = 404, message = "Student not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        return studentService.findById(id)
            .map(student -> {
                studentService.delete(id);
                return ResponseEntity.ok().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
