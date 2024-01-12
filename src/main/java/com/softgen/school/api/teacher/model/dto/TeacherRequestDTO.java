package com.softgen.school.api.teacher.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeacherRequestDTO {

    private String firstName;
    private String lastName;

    @NotBlank(message = "Personal number is required")
    @Pattern(regexp = "^[0-9]{11}$", message = "Invalid personal number format must be 11 digits")
    private String personalNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

}
