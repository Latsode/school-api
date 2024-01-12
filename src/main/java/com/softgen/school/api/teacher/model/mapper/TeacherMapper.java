package com.softgen.school.api.teacher.model.mapper;

import com.softgen.school.api.teacher.model.dao.Teacher;
import com.softgen.school.api.teacher.model.dto.TeacherRequestDTO;
import com.softgen.school.api.teacher.model.dto.TeacherResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    Teacher toEntity(TeacherRequestDTO dto);

    TeacherResponseDTO toDTO(Teacher teacher);

    void updateEntityFromDTO(TeacherRequestDTO dto, @MappingTarget Teacher entity);

}
