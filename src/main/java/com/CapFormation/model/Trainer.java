package com.capFormation.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("trainer")
public class Trainer extends User {

    @Column(name = "speciality" , nullable = false)
    private String speciality;

    @OneToMany(mappedBy = "trainer")
    private Set<Training> trainings = new HashSet<>();

}
