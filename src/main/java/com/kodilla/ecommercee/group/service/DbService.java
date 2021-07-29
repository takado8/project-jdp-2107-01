package com.kodilla.ecommercee.group.service;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.domain.GroupDto;
import com.kodilla.ecommercee.group.repository.GroupDao;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private final GroupDao groupDao;

    public List<Group> getGroups() {
        return groupDao.findAll();
    }

    public Optional<Group> getGroup(Long id){
        return groupDao.findById(id);
    }

    public void createGroup(Group group) {
        groupDao.save(group);
    }

    public void updateGroup(Group group) {
        groupDao.save(group);
    }
}
