package com.capFormation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.capFormation.exception.ResourceNotFoundException;
import com.capFormation.model.Classe;
import com.capFormation.repository.ClassRepository;
import com.capFormation.service.implementation.ClassServiceImpl;

import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ClassTestUnit {
    
    @Mock
    private ClassRepository classRepository;
    
    @InjectMocks
    private ClassServiceImpl classService;
    
    private Classe testClasse;
    
    @BeforeEach
    void setUp() {
        testClasse = new Classe();
        testClasse.setId(1L);
        testClasse.setName("Test Class");
        testClasse.setClassNumber("TC001");
    }
    
    @Test
    void findById_ShouldReturnClass_WhenClassExists() {
        when(classRepository.findById(1L)).thenReturn(Optional.of(testClasse));
        
        Optional<Classe> result = classService.findById(1L);
        
        assertTrue(result.isPresent());
        assertEquals(testClasse.getName(), result.get().getName());
    }
    
    @Test
    void findAll_ShouldReturnPageOfClasses() {
        List<Classe> classes = new ArrayList<>();
        classes.add(testClasse);
        Page<Classe> classPage = new PageImpl<>(classes);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(classRepository.findAll(pageable)).thenReturn(classPage);
        
        Page<Classe> result = classService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }
    
    @Test
    void save_ShouldReturnSavedClass() {
        when(classRepository.save(any(Classe.class))).thenReturn(testClasse);
        
        Classe result = classService.save(testClasse);
        
        assertNotNull(result);
        assertEquals(testClasse.getName(), result.getName());
    }
    
    @Test
    void update_ShouldThrowException_WhenClassNotFound() {
        Classe nonExistentClass = new Classe();
        nonExistentClass.setId(999L);
        
        when(classRepository.existsById(999L)).thenReturn(false);
        
        assertThrows(ResourceNotFoundException.class, () -> {
            classService.update(nonExistentClass);
        });
    }
    
    @Test
    void delete_ShouldThrowException_WhenClassNotFound() {
        when(classRepository.existsById(999L)).thenReturn(false);
        
        assertThrows(ResourceNotFoundException.class, () -> {
            classService.delete(999L);
        });
    }
}
