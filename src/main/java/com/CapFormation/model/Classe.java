package com.capFormation.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set;
import java.util.HashSet;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classes")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "class_number", nullable = false)
    private String classNumber;
    
    @OneToMany(mappedBy = "userClass", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();
    
    // Helper methods
    public void addUser(User user) {
        users.add(user);
        user.setUserClass(this);
    }
    
    public void removeUser(User user) {
        users.remove(user);
        user.setUserClass(null);
    }
}
