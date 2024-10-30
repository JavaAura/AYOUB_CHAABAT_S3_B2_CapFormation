package com.capFormation.service.interfaces;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.capFormation.model.Course;

public interface CourseService {
    Optional<Course> findById(Long id);
    Page<Course> findAll(Pageable pageable);
    Course save(Course course);
    Course update(Course course);
    void delete(Long id);
}
