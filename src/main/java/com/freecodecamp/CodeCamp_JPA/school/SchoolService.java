package com.freecodecamp.CodeCamp_JPA.school;

import com.freecodecamp.CodeCamp_JPA.exception.NameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDto create(SchoolDto dto){
        if(dto.name().isEmpty()){
            throw new NameNotFoundException("Name is Null");
        }
        School school = schoolMapper.toSchool(dto);
        schoolRepository.save(school);
        return dto;
    }

    public List<SchoolDto> findAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }
}
