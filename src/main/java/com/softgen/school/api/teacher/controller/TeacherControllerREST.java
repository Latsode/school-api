package com.softgen.school.api.teacher.controller;

import com.softgen.school.api.teacher.model.dto.TeacherRequestDTO;
import com.softgen.school.api.teacher.model.dto.TeacherResponseDTO;
import com.softgen.school.api.teacher.service.TeacherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherControllerREST {

    private final TeacherService teacherService;

    public TeacherControllerREST(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @PostMapping
    public ResponseEntity<TeacherResponseDTO> saveTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO) {
        return ResponseEntity.ok(teacherService.saveTeacher(teacherRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherRequestDTO teacherRequestDTO) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, teacherRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<TeacherResponseDTO>> searchTeachers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String personalNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {
        return ResponseEntity.ok(teacherService.searchTeachers(firstName, lastName, personalNumber, dateOfBirth));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);

        return ResponseEntity.ok().build();
    }

}
