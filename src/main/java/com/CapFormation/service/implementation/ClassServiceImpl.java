package com.capFormation.service.implementation;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capFormation.model.Classe;
import com.capFormation.service.interfaces.ClassService;
import com.capFormation.repository.ClassRepository;
import com.capFormation.exception.ResourceNotFoundException;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public Optional<Classe> findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public Page<Classe> findAll(Pageable pageable) {
        return classRepository.findAll(pageable);
    }

    @Override
    public Classe save(Classe classRoom) {
        return classRepository.save(classRoom);
    }

    @Override
    public Classe update(Classe classRoom) {
        if (classRoom.getId() == null || !classRepository.existsById(classRoom.getId())) {
            throw new ResourceNotFoundException("Class not found");
        }
        return classRepository.save(classRoom);
    }

    @Override
    public void delete(Long id) {
        if (!classRepository.existsById(id)) {
            throw new ResourceNotFoundException("Class not found");
        }
        classRepository.deleteById(id);
    }

   
}
