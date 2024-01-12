package com.softgen.school.api;

import com.softgen.school.api.group.model.dto.GroupRequestDTO;
import com.softgen.school.api.group.service.GroupService;
import com.softgen.school.api.student.model.dto.StudentRequestDTO;
import com.softgen.school.api.student.service.StudentService;
import com.softgen.school.api.teacher.model.dto.TeacherRequestDTO;
import com.softgen.school.api.teacher.service.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


/***
 * This class is used to load some initial data to database
 * Only for development purposes

 */
@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final GroupService groupService;

    public DataLoader(StudentService studentService, TeacherService teacherService, GroupService groupService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.groupService = groupService;
    }


    @Override
    public void run(String... args){
        StudentRequestDTO studentRequestDTO1 = new StudentRequestDTO();
        studentRequestDTO1.setFirstName("John");
        studentRequestDTO1.setLastName("Doe");
        studentRequestDTO1.setDateOfBirth(LocalDate.of(2000, 1, 1));

        StudentRequestDTO studentRequestDTO2 = new StudentRequestDTO();
        studentRequestDTO2.setFirstName("Jane");
        studentRequestDTO2.setLastName("Doe");
        studentRequestDTO2.setDateOfBirth(LocalDate.of(2000, 6, 1));


        studentService.saveStudent(studentRequestDTO1);
        studentService.saveStudent(studentRequestDTO2);


        TeacherRequestDTO teacherRequestDTO1 = new TeacherRequestDTO();
        teacherRequestDTO1.setFirstName("John");
        teacherRequestDTO1.setLastName("Doe");
        teacherRequestDTO1.setDateOfBirth(LocalDate.of(1980, 1, 1));

        teacherService.saveTeacher(teacherRequestDTO1);


        GroupRequestDTO groupRequestDTO1 = new GroupRequestDTO();
        groupRequestDTO1.setGroupName("Group 1");
        groupRequestDTO1.setGroupNumber(1);


        groupService.saveGroup(groupRequestDTO1);

        groupService.addStudentToGroup(1L, 1L);
        groupService.addStudentToGroup(1L, 2L);
        groupService.addTeacherToGroup(1L, 1L);

    }
}