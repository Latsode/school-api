package com.softgen.school.api.group.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequestDTO {

    @NotBlank(message = "Group name is required")
    private String groupName;
    @Positive(message = "Group number must be positive")
    private Integer groupNumber;

}
