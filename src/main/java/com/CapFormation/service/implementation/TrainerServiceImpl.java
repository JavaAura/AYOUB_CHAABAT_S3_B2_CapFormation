package com.capFormation.service.implementation;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.capFormation.model.Trainer;
import com.capFormation.service.interfaces.TrainerService;
import com.capFormation.repository.TrainerRepository;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public Optional<Trainer> findById(Long id) {
        return trainerRepository.findById(id);
    }

    @Override
    public Page<Trainer> findAll(Pageable pageable) {
        return trainerRepository.findAll(pageable);
    }

    @Override
    public Trainer save(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer update(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public void delete(Long id) {
        trainerRepository.deleteById(id);
    }

}
