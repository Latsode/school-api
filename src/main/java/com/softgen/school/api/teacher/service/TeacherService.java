package com.softgen.school.api.teacher.service;


import com.softgen.school.api.teacher.model.dto.TeacherRequestDTO;
import com.softgen.school.api.teacher.model.dto.TeacherResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {

    TeacherResponseDTO saveTeacher(TeacherRequestDTO teacherRequestDTO);

    TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO teacherRequestDTO);

    List<TeacherResponseDTO> searchTeachers(String firstName, String lastName, String personalNumber, LocalDate dateOfBirth);

    void deleteTeacher(Long id);
}
