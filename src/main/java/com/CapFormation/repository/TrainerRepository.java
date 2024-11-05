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
    

    

    // Validation
    boolean existsByEmail(String email);
}
