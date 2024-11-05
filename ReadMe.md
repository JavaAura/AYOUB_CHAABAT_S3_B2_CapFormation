# CapFormation - Learning Management System

## Overview
CapFormation is a comprehensive learning management system built with Spring Boot, designed to manage educational courses, trainers, students, and classes efficiently.


### User Management
- Single table inheritance design for users (Trainers and Students)
- Secure email validation and unique constraints
- Flexible class and course assignments

### Course Management
- Complete course lifecycle management
- Training status tracking (SCHEDULED, ONGOING, COMPLETED, CANCELLED)
- Start and end date scheduling
- Trainer assignment system
- Student enrollment tracking

### Class Organization
- Unique class numbering system
- Student group management
- Bidirectional relationship with users
- Cascade operations for related entities

## Technical Stack

### Backend
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- PostgreSQL

### Development Tools
- Lombok for boilerplate reduction
- JPA/Hibernate for ORM
- Maven for dependency management

## Entity Relationships
- User (Base class) → Student/Trainer (Inheritance)
- Course ↔ Trainer (ManyToOne)
- Course ↔ Student (OneToMany)
- Class ↔ User (OneToMany)

## Getting Started

### Prerequisites
- JDK 8 or higher
- Maven 3.6+
- Your favorite IDE (IntelliJ IDEA recommended)

### Installation
- bash
- Clone the repository
- git clone https://github.com/yourusername/capformation.git
- Navigate to project directory
- cd capformation
- Build the project
- mvn clean install
- Run the application
- mvn spring-boot:run


## API Endpoints

### Users
- GET /api/users - List all users
- POST /api/users - Create user
- GET /api/users/{id} - Get user by ID
- PUT /api/users/{id} - Update user
- DELETE /api/users/{id} - Delete user

### Courses
- GET /api/courses - List all courses
- POST /api/courses - Create course
- GET /api/courses/{id} - Get course by ID
- PUT /api/courses/{id} - Update course
- DELETE /api/courses/{id} - Delete course

### Classes
- GET /api/classes - List all classes
- POST /api/classes - Create class
- GET /api/classes/{id} - Get class by ID
- PUT /api/classes/{id} - Update class
- DELETE /api/classes/{id} - Delete class


## License
This project is licensed under the MIT License - see the LICENSE.md file for details


## Gestion de Projet
- Git pour le contrôle de version
- JIRA pour la gestion de projet Scrum : [Lien JIRA]

## Diagramme UML
Le diagramme UML du projet est disponible dans le fichier `CapFormation.mdj` dans le dossier `@src` du projet.

## Auteur
Ayoub Chaabat