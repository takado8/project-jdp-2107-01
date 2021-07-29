package com.kodilla.ecommercee.group.repository;

import com.kodilla.ecommercee.group.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GroupDao extends JpaRepository<Group, Long> {
}
