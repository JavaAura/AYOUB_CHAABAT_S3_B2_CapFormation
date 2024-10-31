package com.capFormation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capFormation.model.Course;
import com.capFormation.service.interfaces.CourseService;
import com.capFormation.model.enums.TrainingStatus;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
@Api(value = "Course Management System", description = "Operations pertaining to courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "Get a course by Id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved course"),
        @ApiResponse(code = 404, message = "Course not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.findById(id);
        return course.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "View a list of available courses", response = Page.class)
    @GetMapping
    public ResponseEntity<Page<Course>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.findAll(pageable));
    }

    @ApiOperation(value = "Add a new course")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created course"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        return ResponseEntity.ok(courseService.save(course));
    }

    @ApiOperation(value = "Update an existing course")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated course"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 404, message = "Course not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody Course course) {
        course.setId(id);
        return ResponseEntity.ok(courseService.update(course));
    }

    @ApiOperation(value = "Delete a course")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted course"),
        @ApiResponse(code = 404, message = "Course not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
