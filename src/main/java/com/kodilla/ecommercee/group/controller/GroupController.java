package com.kodilla.ecommercee.group.controller;

import com.kodilla.ecommercee.group.domain.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @GetMapping(path = "getAllGroups")
    public List<GroupDto> getAllGroups() {
        return new ArrayList<>();
    }

    @GetMapping(path = "getGroup")
    public GroupDto getGroup(Long groupId) {
        return new GroupDto();
    }

    @PostMapping(path = "createGroup")
    public void createGroup(GroupDto groupDto) {

    }

    @PutMapping(path = "updateGroup")
    public GroupDto updateGroup(GroupDto groupDto) {
        return new GroupDto();
    }
}