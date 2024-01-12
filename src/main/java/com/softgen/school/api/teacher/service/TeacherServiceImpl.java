package com.softgen.school.api.teacher.service;


import com.softgen.school.api.teacher.model.dao.Teacher;
import com.softgen.school.api.teacher.model.dto.TeacherRequestDTO;
import com.softgen.school.api.teacher.model.dto.TeacherResponseDTO;
import com.softgen.school.api.teacher.model.enums.TeacherStatus;
import com.softgen.school.api.teacher.model.mapper.TeacherMapper;
import com.softgen.school.api.teacher.repository.TeacherRepositoryJPA;
import com.softgen.school.api.teacher.specification.TeacherSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryJPA teacherRepositoryJPA;

    public TeacherServiceImpl(TeacherRepositoryJPA teacherRepositoryJPA) {
        this.teacherRepositoryJPA = teacherRepositoryJPA;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public TeacherResponseDTO saveTeacher(TeacherRequestDTO teacherRequestDTO) {
        // create teacher object from request
        Teacher teacher = TeacherMapper.INSTANCE.toEntity(teacherRequestDTO);

        // save teacher object to database
        Teacher savedTeacher= teacherRepositoryJPA.save(teacher);

        // create response object from saved teacher
        TeacherResponseDTO teacherResponseDTO = TeacherMapper.INSTANCE.toDTO(savedTeacher);


        return teacherResponseDTO;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO teacherRequestDTO) {
        //Make sure that teacher with given id exists
        Teacher existingTeacher = teacherRepositoryJPA.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + id));

        //Update existing teacher with new data
        TeacherMapper.INSTANCE.updateEntityFromDTO(teacherRequestDTO, existingTeacher);

        //Save updated teacher to database
        Teacher updatedTeacher = teacherRepositoryJPA.save(existingTeacher);

        //Create response object from updated teacher
        TeacherResponseDTO teacherResponseDTO = TeacherMapper.INSTANCE.toDTO(updatedTeacher);

        return teacherResponseDTO;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<TeacherResponseDTO> searchTeachers(String firstName, String lastName, String personalNumber, LocalDate dateOfBirth) {
        //Add search parameters to specification
        Specification<Teacher> specification = TeacherSpecification.searchByMultipleFields(firstName, lastName, personalNumber, dateOfBirth);

        //Get teachers from database using specification
        List<Teacher> teachers = teacherRepositoryJPA.findAll(specification);

        //Create responses from teachers
        List<TeacherResponseDTO> teacherResponseDTOS = teachers.stream()
                .map(TeacherMapper.INSTANCE::toDTO)
                .toList();

        return teacherResponseDTOS;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTeacher(Long id) {
        //Make sure that teacher with given id exists
        Teacher teacher = teacherRepositoryJPA.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + id));

        //Update teacher status to DELETED
        teacher.setStatus(TeacherStatus.DELETED);

        //Save deleted teacher to database
        teacherRepositoryJPA.save(teacher);

    }
}
