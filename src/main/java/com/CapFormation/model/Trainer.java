package com.capFormation.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("trainer")
public class Trainer extends User {

    @Column(name = "speciality", nullable = true)
    private String speciality;
    
    @OneToMany(mappedBy = "trainer")
    private Set<Course> courses = new HashSet<>();
}
