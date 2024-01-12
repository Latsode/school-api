package com.softgen.school.api.student.service;

import com.softgen.school.api.student.model.dao.Student;
import com.softgen.school.api.student.model.dto.StudentRequestDTO;
import com.softgen.school.api.student.model.dto.StudentResponseDTO;
import com.softgen.school.api.student.model.enums.StudentStatus;
import com.softgen.school.api.student.model.mapper.StudentMapper;
import com.softgen.school.api.student.repository.StudentRepositoryJPA;
import com.softgen.school.api.student.specification.StudentSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryJPA studentRepositoryJPA;

    public StudentServiceImpl(StudentRepositoryJPA studentRepositoryJPA) {
        this.studentRepositoryJPA = studentRepositoryJPA;
    }


    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO) {
        // create student object from request
        Student student = StudentMapper.INSTANCE.toEntity(studentRequestDTO);

        // save student object to database
        Student savedStudent= studentRepositoryJPA.save(student);

        // create response object from saved student
        StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(savedStudent);


        return studentResponseDTO;

    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        //Make sure that student with given id exists
        Student existingStudent = studentRepositoryJPA.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));

        //Update existing student with new data
        StudentMapper.INSTANCE.updateEntityFromDTO(studentRequestDTO, existingStudent);

        //Save updated student to database
        Student updatedStudent = studentRepositoryJPA.save(existingStudent);

        //Create response object from updated student
        StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(updatedStudent);

        return studentResponseDTO;
    }

    @Override
    public List<StudentResponseDTO> searchStudents(String firstName, String lastName, String personalNumber, LocalDate dateOfBirth) {
        //Add search parameters to specification
        Specification<Student> specification = StudentSpecification.searchByMultipleFields(firstName, lastName, personalNumber, dateOfBirth);

        //Get students from database using specification
        List<Student> students = studentRepositoryJPA.findAll(specification);

        //Create responses from students
        List<StudentResponseDTO> studentResponseDTOS = students.stream()
                .map(StudentMapper.INSTANCE::toDTO)
                .toList();

        return studentResponseDTOS;

    }

    @Override
    public void deleteStudent(Long id) {
        //Make sure that student with given id exists
        Student student = studentRepositoryJPA.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));

        //Update student status to DELETED
        student.setStatus(StudentStatus.DELETED);

        //Save deleted student to database
        studentRepositoryJPA.save(student);

    }
}
