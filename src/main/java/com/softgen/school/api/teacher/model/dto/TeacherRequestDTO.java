package com.softgen.school.api.teacher.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeacherRequestDTO {

    private String firstName;
    private String lastName;
    private String personalNumber;
    private String email;
    private LocalDate dateOfBirth;

}
