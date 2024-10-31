package com.capFormation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capFormation.model.Classe;
import com.capFormation.service.interfaces.ClassService;

import javax.validation.Valid;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
@Api(value = "Class Management System", description = "Operations pertaining to classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @ApiOperation(value = "Get a class by Id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved class"),
        @ApiResponse(code = 404, message = "Class not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClassById(@PathVariable Long id) {
        Optional<Classe> classe = classService.findById(id);
        return classe.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "View a list of available classes", response = Page.class)
    @GetMapping
    public ResponseEntity<Page<Classe>> getAllClasses(Pageable pageable) {
        return ResponseEntity.ok(classService.findAll(pageable));
    }

    @ApiOperation(value = "Add a new class")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created class"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Classe> createClass(@Valid @RequestBody Classe classe) {
        return ResponseEntity.ok(classService.save(classe));
    }

    @ApiOperation(value = "Update an existing class")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated class"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 404, message = "Class not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Classe> updateClass(@PathVariable Long id, @Valid @RequestBody Classe classe) {
        classe.setId(id);
        return ResponseEntity.ok(classService.update(classe));
    }

    @ApiOperation(value = "Delete a class")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted class"),
        @ApiResponse(code = 404, message = "Class not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classService.delete(id);
        return ResponseEntity.ok().build();
    }
}
