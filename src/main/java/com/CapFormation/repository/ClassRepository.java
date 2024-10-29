package com.capFormation.repository;

import com.capFormation.model.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    // Read operations
    Page<Class> findByName(String name, Pageable pageable);
    Optional<Class> findByClassNumber(String classNumber);
    
    
    // Validation
    boolean existsByClassNumber(String classNumber);
}
