package com.softgen.school.api.group.model.dto;

import com.softgen.school.api.group.model.enums.GroupStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupResponseDTO {

    private Long id;
    private String groupName;
    private Integer groupNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private GroupStatus status;

    private Integer studentCount;
    private Integer teacherCount;

}