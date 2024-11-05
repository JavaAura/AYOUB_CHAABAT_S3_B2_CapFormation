package com.capFormation.repository;

import com.capFormation.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Basic finders
    Page<Student> findByEmail(String email, Pageable pageable);
    Page<Student> findByLevel(String level, Pageable pageable);
    
  
    
    // Validation
    boolean existsByEmail(String email);
}
