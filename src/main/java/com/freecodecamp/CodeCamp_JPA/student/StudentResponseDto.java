package com.freecodecamp.CodeCamp_JPA.student;

public record StudentResponseDto(
        String firstname,
        String lastname,
        String email
) {
}
