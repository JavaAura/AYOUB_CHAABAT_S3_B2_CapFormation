package com.capFormation.repository;

import com.capFormation.model.Trainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    // Basic finder methods
    Page<Trainer> findByEmail(String email, Pageable pageable);
    Page<Trainer> findBySpeciality(String speciality, Pageable pageable);
    Page<Trainer> findByLastNameAndFirstName(String lastName, String firstName, Pageable pageable);
    

    
    // Class-related queries
    Page<Trainer> findByUserClass_Id(Long classId, Pageable pageable);
    
    // Course-related queries
    @Query("SELECT t FROM Trainer t WHERE (SELECT COUNT(c) FROM Course c WHERE c.trainer = t) < :maxCourses")
    Page<Trainer> findAvailableTrainers(@Param("maxCourses") int maxCourses, Pageable pageable);
    
    @Query("SELECT t FROM Trainer t WHERE NOT EXISTS (SELECT c FROM Course c WHERE c.trainer = t)")
    Page<Trainer> findTrainersWithoutCourses(Pageable pageable);
    
    // Validation
    boolean existsByEmail(String email);
}
