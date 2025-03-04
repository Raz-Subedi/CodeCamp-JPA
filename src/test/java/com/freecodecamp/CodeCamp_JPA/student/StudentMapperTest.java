package com.freecodecamp.CodeCamp_JPA.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto("John",
                "Doe",
                "john@gmail.com",
                1
        );

        Student student = mapper.toStudent(dto);
        assertEquals(dto.firstname(), student.getFirstName());
        assertEquals(dto.lastname(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null() {
       var exp = assertThrows(NullPointerException.class,() -> mapper.toStudent(null));
       assertEquals("The student Dto should not be null",exp.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        //Given
        Student student = new Student("Raz",
                "Subedi",
                "raz@gmail.com",
                23
        );

        //When
        StudentResponseDto dto = mapper.toStudentResponseDto(student);

        //Then
        assertEquals(student.getFirstName(), dto.firstname());
        assertEquals(student.getLastName(), dto.lastname());
        assertEquals(student.getEmail(), dto.email());
    }

}