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

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClassById(@PathVariable Long id) {
        Optional<Classe> classe = classService.findById(id);
        return classe.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Classe>> getAllClasses(Pageable pageable) {
        return ResponseEntity.ok(classService.findAll(pageable));
    }

 

    @PostMapping
    public ResponseEntity<Classe> createClass(@Valid @RequestBody Classe classe) {
        return ResponseEntity.ok(classService.save(classe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> updateClass(@PathVariable Long id, @Valid @RequestBody Classe classe) {
        classe.setId(id);
        return ResponseEntity.ok(classService.update(classe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classService.delete(id);
        return ResponseEntity.ok().build();
    }
}
