package com.softgen.school.api.group.service;

import com.softgen.school.api.group.model.dto.GroupRequestDTO;
import com.softgen.school.api.group.model.dto.GroupResponseDTO;

import java.util.List;

public interface GroupService {

    GroupResponseDTO saveGroup(GroupRequestDTO groupRequestDTO);

    GroupResponseDTO updateGroup(Long id, GroupRequestDTO groupRequestDTO);

    GroupResponseDTO findGroupByGroupNumber(Integer groupNumber);

    void deleteGroup(Long id);

    List<GroupResponseDTO> findAllGroups();

    GroupResponseDTO addStudentToGroup(Long groupId, Long studentId);

    GroupResponseDTO addTeacherToGroup(Long groupId, Long teacherId);

}
