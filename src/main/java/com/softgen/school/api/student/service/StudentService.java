package com.softgen.school.api.student.service;

import com.softgen.school.api.student.model.dto.StudentRequestDTO;
import com.softgen.school.api.student.model.dto.StudentResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {

    StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);

    List<StudentResponseDTO> searchStudents(String firstName, String lastName, String personalNumber, LocalDate dateOfBirth);

    void deleteStudent(Long id);
}
