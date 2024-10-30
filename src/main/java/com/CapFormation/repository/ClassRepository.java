package com.capFormation.repository;

import com.capFormation.model.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Classe, Long> {
    // Read operations
    Page<Classe> findByName(String name, Pageable pageable);
    Optional<Classe> findByClassNumber(String classNumber);
    
    
    // Validation
    boolean existsByClassNumber(String classNumber);
   
}
