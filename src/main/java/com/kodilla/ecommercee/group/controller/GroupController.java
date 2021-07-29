package com.kodilla.ecommercee.group.controller;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.domain.GroupDto;
import com.kodilla.ecommercee.group.mapper.GroupMapper;
import com.kodilla.ecommercee.group.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.GroupDefinitionException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {
    private final DbService dbService;
    private final GroupMapper mapper;

    @GetMapping(path = "getGroups")
    public List<GroupDto> getGroups() {
        return mapper.mapGroupListToDtoList(dbService.getGroups());
    }

    @GetMapping(path = "getGroup")
    public GroupDto getGroup(@RequestParam Long groupId) {
        return mapper.mapGroupToDto(dbService.getGroup(groupId).
                orElseThrow(() -> new GroupNotFoundException("Group of id: '" + groupId + "' does not exist")));
    }

    @PostMapping(path = "createGroup")
    public void createGroup(@RequestBody GroupDto groupDto) {
        dbService.createGroup(mapper.mapDtoToGroup(groupDto));
    }

    @PutMapping(path = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        dbService.updateGroup(mapper.mapDtoToGroup(groupDto));
        return groupDto;
    }
}