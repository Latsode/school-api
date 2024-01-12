package com.softgen.school.api.student.controller;

import com.softgen.school.api.student.model.dto.StudentRequestDTO;
import com.softgen.school.api.student.model.dto.StudentResponseDTO;
import com.softgen.school.api.student.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentControllerREST {

    private final StudentService studentService;

    public StudentControllerREST(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<StudentResponseDTO> saveStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.ok(studentService.saveStudent(studentRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> searchStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String personalNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {
        return ResponseEntity.ok(studentService.searchStudents(firstName, lastName, personalNumber, dateOfBirth));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);

        return ResponseEntity.ok().build();
    }

}
