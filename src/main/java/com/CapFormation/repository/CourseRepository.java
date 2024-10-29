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
    
    // Date-based queries
    Page<Course> findByStartDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Course> findByEndDateAfter(LocalDate date, Pageable pageable);
    
    // Search
    @Query("SELECT c FROM Course c WHERE c.name LIKE %:keyword% OR c.level LIKE %:keyword%")
    Page<Course> searchCourses(@Param("keyword") String keyword, Pageable pageable);
    
    // Status-based queries
    @Query("SELECT c FROM Course c WHERE c.startDate <= :currentDate AND c.endDate >= :currentDate")
    Page<Course> findActiveCourses(@Param("currentDate") LocalDate currentDate, Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.startDate > :currentDate")
    Page<Course> findUpcomingCourses(@Param("currentDate") LocalDate currentDate, Pageable pageable);
    
    // Validation
    boolean existsByNameAndStartDateAndEndDate(String name, LocalDate startDate, LocalDate endDate);
}
