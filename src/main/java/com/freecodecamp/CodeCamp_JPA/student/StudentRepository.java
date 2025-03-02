package com.freecodecamp.CodeCamp_JPA.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    public List<Student> findAllByFirstNameContaining(String firstName);
}
