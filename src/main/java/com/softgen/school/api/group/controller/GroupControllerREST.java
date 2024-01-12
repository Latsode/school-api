package com.softgen.school.api.group.controller;

import com.softgen.school.api.group.model.dto.GroupRequestDTO;
import com.softgen.school.api.group.model.dto.GroupResponseDTO;
import com.softgen.school.api.group.service.GroupService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupControllerREST {

    private final GroupService groupService;

    public GroupControllerREST(GroupService groupService) {
        this.groupService = groupService;
    }
    @PostMapping
    public ResponseEntity<GroupResponseDTO> saveGroup(@RequestBody GroupRequestDTO groupRequestDTO) {
        return ResponseEntity.ok(groupService.saveGroup(groupRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> updateGroup(@PathVariable Long id, @RequestBody GroupRequestDTO groupRequestDTO) {
        return ResponseEntity.ok(groupService.updateGroup(id, groupRequestDTO));
    }
    @GetMapping("/group-number")
    public ResponseEntity<GroupResponseDTO> findGroupByGroupNumber(@RequestParam Integer groupNumber) {
        return ResponseEntity.ok(groupService.findGroupByGroupNumber(groupNumber));
    }

    @GetMapping
    public ResponseEntity<List<GroupResponseDTO>> findAllGroups() {
        return ResponseEntity.ok(groupService.findAllGroups());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);

        return ResponseEntity.ok().build();
    }

}