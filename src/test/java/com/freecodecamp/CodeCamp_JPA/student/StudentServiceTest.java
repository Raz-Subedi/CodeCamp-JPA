package com.freecodecamp.CodeCamp_JPA.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    // Which service we want to test
    @InjectMocks
    private StudentService studentService;

    // declare the dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        // Given
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "john@email.com",
                1
        );

        Student student = new Student(
                "John",
                "Doe",
                "john@email.com",
                23
        );

        // Ensure saved student has an ID
        Student savedStudent = new Student(
                "John",
                "Doe",
                "john@email.com",
                23
        );
        savedStudent.setId(1); // Simulate the database assigning an ID

        StudentResponseDto expectedResponse = new StudentResponseDto(
                "John",
                "Doe",
                "john@email.com"
        );

        // Mock the Calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent); // Return savedStudent instead of student
        when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(expectedResponse);

        // When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        // Then
        assertNotNull(responseDto, "Response DTO should not be null"); // Prevent NullPointerException
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        verify(studentMapper,times(1)).toStudent(dto);
        verify(studentRepository,times(1)).save(student);
        verify(studentMapper,times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_students(){
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Doe",
                "john@email.com",
                23
        ));

        //Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John",
                        "Doe",
                        "john@example.com")
                );

        // When
        List<StudentResponseDto> responseDtos = studentService.findAllStudents();

        //Then
        assertEquals(students.size(),responseDtos.size());

        verify(studentRepository,times(1)).findAll();
      //  verify(studentMapper,times(1)).toStudentResponseDto();
    }

    @Test
    public void should_return_student_by_id(){
        //Given
        Integer studentId = 1;
        Student student = new Student(
                "John",
                "Doe",
                "john@email.com",
                23
        );

        //Mock the calls
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John",
                        "Doe",
                        "john@email.com")
                );

        //When
        StudentResponseDto dto = studentService.findStudentByID(studentId);

        //Then
        assertEquals(dto.firstname(), student.getFirstName());
        assertEquals(dto.lastname(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());

        verify(studentRepository,times(1)).findById(studentId);
    }

    @Test
    public void should_return_student_by_name(){
        //Given
        String name = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Doe",
                "john@email.com",
                23
        ));

        //Mock the calls
        when(studentRepository.findAllByFirstNameContaining(name)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John",
                        "Doe",
                        "john@email.com")
                );

        //When
        List<StudentResponseDto> responseDtos = studentService.findStudentByName(name);

        //Then
        assertEquals(students.size(),responseDtos.size());
        verify(studentRepository,times(1)).findAllByFirstNameContaining(name);
    }

}