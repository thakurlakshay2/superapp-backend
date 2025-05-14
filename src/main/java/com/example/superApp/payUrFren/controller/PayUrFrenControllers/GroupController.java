package com.example.superApp.payUrFren.controller.PayUrFrenControllers;

import com.example.superApp.payUrFren.dto.CreateGroupDTO;
import com.example.superApp.payUrFren.entity.Group;
import com.example.superApp.payUrFren.service.payUrFren.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payUrFren/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    @GetMapping
    public ResponseEntity<Group> getGroupList(@RequestBody CreateGroupDTO dto) {
        return ResponseEntity.ok(groupService.createGroup(dto));
    }
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody CreateGroupDTO dto) {
        return ResponseEntity.ok(groupService.createGroup(dto));
    }
}
