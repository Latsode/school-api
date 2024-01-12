package com.softgen.school.api.student.repository;

import com.softgen.school.api.student.model.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepositoryJPA extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {
}
