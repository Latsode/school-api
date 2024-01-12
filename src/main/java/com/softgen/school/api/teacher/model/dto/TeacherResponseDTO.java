package com.softgen.school.api.teacher.model.dto;

import com.softgen.school.api.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TeacherResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String personalNumber;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StudentStatus status;

}