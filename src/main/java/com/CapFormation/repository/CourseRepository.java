package com.capFormation.repository;

import com.capFormation.model.Course;
import com.capFormation.model.enums.TrainingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Read operations
    Page<Course> findByName(String name, Pageable pageable);
    Page<Course> findByLevel(String level, Pageable pageable);
    Page<Course> findByStatus(TrainingStatus status, Pageable pageable);
    Page<Course> findByTrainer_Id(Long trainerId, Pageable pageable);
    
 
    
     
    
    // Validation
    boolean existsByNameAndStartDateAndEndDate(String name, LocalDate startDate, LocalDate endDate);
}
