package com.softgen.school.api.group.service;

import com.softgen.school.api.group.model.dao.Group;
import com.softgen.school.api.group.model.dto.GroupRequestDTO;
import com.softgen.school.api.group.model.dto.GroupResponseDTO;
import com.softgen.school.api.group.model.enums.GroupStatus;
import com.softgen.school.api.group.model.mapper.GroupMapper;
import com.softgen.school.api.group.repository.GroupRepositoryJPA;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepositoryJPA groupRepositoryJPA;

    public GroupServiceImpl(GroupRepositoryJPA groupRepositoryJPA) {
        this.groupRepositoryJPA = groupRepositoryJPA;
    }


    @Override
    public GroupResponseDTO saveGroup(GroupRequestDTO groupRequestDTO) {
        // create group object from request
        Group group = GroupMapper.INSTANCE.toEntity(groupRequestDTO);

        // save group object to database
        Group savedGroup= groupRepositoryJPA.save(group);

        // create response object from saved group
        GroupResponseDTO groupResponseDTO = GroupMapper.INSTANCE.toDTO(savedGroup);


        return groupResponseDTO;

    }

    @Override
    public GroupResponseDTO updateGroup(Long id, GroupRequestDTO groupRequestDTO) {
        //Make sure that group with given id exists
        Group existingGroup = groupRepositoryJPA.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with ID: " + id));

        //Update existing group with new data
        GroupMapper.INSTANCE.updateEntityFromDTO(groupRequestDTO, existingGroup);

        //Save updated group to database
        Group updatedGroup = groupRepositoryJPA.save(existingGroup);

        //Create response object from updated group
        GroupResponseDTO groupResponseDTO = GroupMapper.INSTANCE.toDTO(updatedGroup);

        return groupResponseDTO;
    }

    @Override
    public GroupResponseDTO findGroupByGroupNumber(Integer groupNumber) {

        Group group = groupRepositoryJPA.findByGroupNumber(groupNumber)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with Group Number: " + groupNumber));


        //convert group to response
        GroupResponseDTO groupResponseDTO = GroupMapper.INSTANCE.toDTO(group);

        return groupResponseDTO;

    }

    @Override
    public void deleteGroup(Long id) {
        //Make sure that group with given id exists
        Group group = groupRepositoryJPA.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with ID: " + id));

        //Update group status to DELETED
        group.setStatus(GroupStatus.DELETED);

        //Save deleted group to database
        groupRepositoryJPA.save(group);

    }

    @Override
    public List<GroupResponseDTO> findAllGroups() {
        //Get groups from database
        List<Group> groups = groupRepositoryJPA.findAll();

        //Create responses from groups
        List<GroupResponseDTO> groupResponseDTOS = groups.stream()
                .map(GroupMapper.INSTANCE::toDTO)
                .toList();

        return groupResponseDTOS;
    }
}
