package com.freecodecamp.CodeCamp_JPA;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    public Student post(
            @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/students/{student-id}")
    public Student findStudentByID(
            @PathVariable("student-id") int id){
        return studentRepository.findById(id).orElse(new Student());
    }

    @GetMapping("/students")
    public List<Student> finsAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> findStudentByName(
            @PathVariable("student-name") String name) {
        return studentRepository.findAllByFirstNameContaining(name);
    }

    @DeleteMapping("/delete/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStudentById(@PathVariable("student-id") int id) {
        studentRepository.deleteById(id);
        return "Student deleted of id: "+id;
    }
}
