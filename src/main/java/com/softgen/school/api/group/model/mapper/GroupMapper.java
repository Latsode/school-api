package com.softgen.school.api.group.model.mapper;

import com.softgen.school.api.group.model.dao.Group;
import com.softgen.school.api.group.model.dto.GroupRequestDTO;
import com.softgen.school.api.group.model.dto.GroupResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    Group toEntity(GroupRequestDTO dto);

    GroupResponseDTO toDTO(Group group);

    void updateEntityFromDTO(GroupRequestDTO dto, @MappingTarget Group entity);

}
