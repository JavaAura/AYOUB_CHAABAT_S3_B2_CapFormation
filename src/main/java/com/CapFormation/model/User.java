package com.capFormation.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
 

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    @Column(name = "firstName" , nullable = false)
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    @Column(name = "lastName" , nullable = false)
    private String lastName;
    
    
    @NotNull(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(name = "email", unique = true , nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class userClass;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
