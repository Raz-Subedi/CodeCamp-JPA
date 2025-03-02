package com.freecodecamp.CodeCamp_JPA.student;

import com.freecodecamp.CodeCamp_JPA.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto){
        Student student = new Student();
        student.setFirstName(dto.firstname());
        student.setLastName(dto.lastname());
        student.setEmail(dto.email());

        School school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);
        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
}
