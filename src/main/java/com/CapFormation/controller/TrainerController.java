package com.capFormation.controller;

import com.capFormation.model.Trainer;
import com.capFormation.service.interfaces.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/trainers")
@Api(value = "Trainer Management System", description = "Operations pertaining to trainers")
public class TrainerController {
    
    @Autowired
    private TrainerService trainerService;

    @ApiOperation(value = "View a list of available trainers", response = Page.class)
    @GetMapping
    public Page<Trainer> getAllTrainers(Pageable pageable) {
        return trainerService.findAll(pageable);
    }

    @ApiOperation(value = "Get a trainer by Id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved trainer"),
        @ApiResponse(code = 404, message = "Trainer not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        return trainerService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Add a new trainer")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created trainer"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping
    public Trainer createTrainer(@Valid @RequestBody Trainer trainer) {
        return trainerService.save(trainer);
    }

    @ApiOperation(value = "Update an existing trainer")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated trainer"),
        @ApiResponse(code = 404, message = "Trainer not found"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id, @Valid @RequestBody Trainer trainer) {
        return trainerService.findById(id)
            .<ResponseEntity<Trainer>>map(existingTrainer -> {
                trainer.setId(id);
                return ResponseEntity.ok(trainerService.save(trainer));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Delete a trainer")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted trainer"),
        @ApiResponse(code = 404, message = "Trainer not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        return trainerService.findById(id)
            .map(trainer -> {
                trainerService.delete(id);
                return ResponseEntity.ok().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
