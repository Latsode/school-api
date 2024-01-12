package com.softgen.school.api.group.controller;

import com.softgen.school.api.group.model.dto.GroupRequestDTO;
import com.softgen.school.api.group.model.dto.GroupResponseDTO;
import com.softgen.school.api.group.service.GroupService;
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

    @PostMapping("/{groupId}/students/{studentId}")
    public ResponseEntity<GroupResponseDTO> addStudentToGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        GroupResponseDTO updatedGroup = groupService.addStudentToGroup(groupId, studentId);
        return ResponseEntity.ok(updatedGroup);
    }

    @PostMapping("/{groupId}/teachers/{teacherId}")
    public ResponseEntity<GroupResponseDTO> addTeacherToGroup(@PathVariable Long groupId, @PathVariable Long teacherId) {
        GroupResponseDTO updatedGroup = groupService.addTeacherToGroup(groupId, teacherId);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/{groupId}/students/{studentId}")
    public ResponseEntity<GroupResponseDTO> removeStudentFromGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        GroupResponseDTO updatedGroup = groupService.removeStudentFromGroup(groupId, studentId);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/{groupId}/teachers/{teacherId}")
    public ResponseEntity<GroupResponseDTO> removeTeacherFromGroup(@PathVariable Long groupId, @PathVariable Long teacherId) {
        GroupResponseDTO updatedGroup = groupService.removeTeacherFromGroup(groupId, teacherId);
        return ResponseEntity.ok(updatedGroup);
    }


}
