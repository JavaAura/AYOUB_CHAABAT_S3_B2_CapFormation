package com.capFormation.model;

import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("student")
public class Student extends User {


    @Column(name = "level" , nullable = true)
    private String level;

    

}
