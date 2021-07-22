package com.kodilla.ecommercee.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "status")
    private String status;

    @Column(name = "userKey")
    private Long userKey;
}
