package com.capFormation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.capFormation.model.Classe;
import com.capFormation.service.interfaces.ClassService;
import com.capFormation.exception.ResourceNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ClassTestIntegration {

    @Autowired
    private ClassService classService;
    
    @Test
    void createAndRetrieveClass() {
        // Create a new class
        Classe classe = new Classe();
        classe.setName("Integration Test Class");
        classe.setClassNumber("ITC001");
        
        // Save the class
        Classe savedClasse = classService.save(classe);
        assertNotNull(savedClasse.getId());
        
        // Retrieve the class
        Classe retrievedClasse = classService.findById(savedClasse.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
            
        assertEquals("Integration Test Class", retrievedClasse.getName());
        assertEquals("ITC001", retrievedClasse.getClassNumber());
    }
    
    @Test
    void updateClass() {
        // Create and save a class
        Classe classe = new Classe();
        classe.setName("Original Name");
        classe.setClassNumber("ON001");
        Classe savedClasse = classService.save(classe);
        
        // Update the class
        savedClasse.setName("Updated Name");
        Classe updatedClasse = classService.update(savedClasse);
        
        assertEquals("Updated Name", updatedClasse.getName());
    }
    
    @Test
    void deleteClass() {
        // Create and save a class
        Classe classe = new Classe();
        classe.setName("To Be Deleted");
        classe.setClassNumber("TBD001");
        Classe savedClasse = classService.save(classe);
        
        // Delete the class
        classService.delete(savedClasse.getId());
        
        // Verify deletion
        assertThrows(ResourceNotFoundException.class, () -> {
            classService.findById(savedClasse.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        });
    }
    
    @Test
    void findAllClasses() {
        // Create and save multiple classes
        for (int i = 0; i < 3; i++) {
            Classe classe = new Classe();
            classe.setName("Test Class " + i);
            classe.setClassNumber("TC00" + i);
            classService.save(classe);
        }
        
        // Retrieve all classes
        Page<Classe> classPage = classService.findAll(PageRequest.of(0, 10));
        
        assertNotNull(classPage);
        assertTrue(classPage.getContent().size() >= 3);
    }
}
