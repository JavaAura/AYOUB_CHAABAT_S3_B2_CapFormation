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
    
    // Name-based queries
    Page<Student> findByLastNameAndFirstName(String lastName, String firstName, Pageable pageable);
    
    // Class and training related queries
    Page<Student> findByUserClass_Id(Long classId, Pageable pageable);
    Page<Student> findByTraining_Id(Long trainingId, Pageable pageable);
    
    
    // Status queries
    @Query("SELECT s FROM Student s WHERE s.training IS NULL")
    Page<Student> findStudentsWithoutTraining(Pageable pageable);
    
    // Validation
    boolean existsByEmail(String email);
}
