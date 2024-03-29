package com.softgen.school.api.teacher.repository;

import com.softgen.school.api.teacher.model.dao.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherRepositoryJPA extends JpaRepository<Teacher,Long>, JpaSpecificationExecutor<Teacher> {
}
