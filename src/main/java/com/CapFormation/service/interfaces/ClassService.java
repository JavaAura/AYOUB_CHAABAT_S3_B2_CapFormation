package com.capFormation.service.interfaces;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.capFormation.model.Classe;
public interface ClassService {

    Optional<Classe> findById(Long id);
    Page<Classe > findAll(Pageable pageable);
    Classe save(Classe classRoom);
    Classe update(Classe classRoom);
    void delete(Long id);   
    
    
    
}
