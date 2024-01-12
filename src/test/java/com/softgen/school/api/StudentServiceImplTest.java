package com.softgen.school.api;

import com.softgen.school.api.student.model.dao.Student;
import com.softgen.school.api.student.model.dto.StudentRequestDTO;
import com.softgen.school.api.student.model.dto.StudentResponseDTO;
import com.softgen.school.api.student.model.enums.StudentStatus;
import com.softgen.school.api.student.repository.StudentRepositoryJPA;
import com.softgen.school.api.student.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepositoryJPA studentRepositoryJPA;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void whenSaveStudent_thenReturnsStudentResponseDTO() {
        // Arrange
        StudentRequestDTO requestDTO = new StudentRequestDTO();
        requestDTO.setFirstName("John");
        requestDTO.setEmail("nika@gmail.com");
        requestDTO.setPersonalNumber("12345678901");
        requestDTO.setDateOfBirth(LocalDate.of(2000, 1, 1));

        Student mockStudent = new Student();
        mockStudent.setFirstName("John");
        mockStudent.setEmail("nika@gmail.com");
        mockStudent.setPersonalNumber("12345678901");
        mockStudent.setDateOfBirth(LocalDate.of(2000, 1, 1));
        mockStudent.setStatus(StudentStatus.ACTIVE);

        when(studentRepositoryJPA.save(any(Student.class))).thenReturn(mockStudent);

        // Act
        StudentResponseDTO result = studentService.saveStudent(requestDTO);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo(requestDTO.getFirstName());
        assertThat(result.getEmail()).isEqualTo(requestDTO.getEmail());
        assertThat(result.getPersonalNumber()).isEqualTo(requestDTO.getPersonalNumber());
        assertThat(result.getDateOfBirth()).isEqualTo(requestDTO.getDateOfBirth());
        assertThat(result.getStatus()).isEqualTo(StudentStatus.ACTIVE);
        
        verify(studentRepositoryJPA).save(any(Student.class));
    }

}
