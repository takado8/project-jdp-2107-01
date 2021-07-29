package com.kodilla.ecommercee.group.mapper;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.domain.GroupDto;
import org.springframework.stereotype.Service;

@Service
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto) {
        return new Group();
    }
}