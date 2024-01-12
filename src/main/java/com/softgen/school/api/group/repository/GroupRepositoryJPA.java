package com.softgen.school.api.group.repository;

import com.softgen.school.api.group.model.dao.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepositoryJPA extends JpaRepository<Group,Long>{
    Optional<Group> findByGroupNumber(Integer groupNumber);
}
