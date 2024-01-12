package com.softgen.school.api.student.model.mapper;

import com.softgen.school.api.student.model.dao.Student;
import com.softgen.school.api.student.model.dto.StudentRequestDTO;
import com.softgen.school.api.student.model.dto.StudentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentRequestDTO dto);

    StudentResponseDTO toDTO(Student student);

    void updateEntityFromDTO(StudentRequestDTO dto, @MappingTarget Student entity);

}
