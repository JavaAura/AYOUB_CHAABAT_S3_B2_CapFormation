package com.capFormation.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.capFormation.model.Trainer;

public interface TrainerService {

    Optional<Trainer> findById(Long id);
    Page<Trainer> findAll(Pageable pageable);
    Trainer save(Trainer trainer);
    Trainer update(Trainer trainer);
    void delete(Long id);   

}
