package com.capFormation.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.capFormation.model.Student;

public interface StudentService {

    Optional<Student> findById(Long id);
    Page<Student> findAll(Pageable pageable);
    Student save(Student student);
    Student update(Student student);
    void delete(Long id);

}
