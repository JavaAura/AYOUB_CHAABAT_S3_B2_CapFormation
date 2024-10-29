package com.capFormation.model;

import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ManyToMany;
import java.util.Set;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("learner")
public class Learner extends User {


    @Column(name = "level" , nullable = false)
    private String level;

    @ManyToMany(mappedBy = "learners")
    private Set<Training> trainings = new HashSet<>();

}
