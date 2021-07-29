package com.kodilla.ecommercee.group.mapper;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.domain.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {
    public GroupDto mapGroupToDto(Group group) {
        return new GroupDto(group.getId(), group.getName(), group.getProducts());
    }

    public Group mapDtoToGroup(GroupDto dto) {
        return new Group(dto.getId(), dto.getName(), dto.getProducts());
    }

    public List<GroupDto> mapGroupListToDtoList(List<Group> groups) {
        return groups.stream().map(this::mapGroupToDto).collect(Collectors.toList());
    }
}
