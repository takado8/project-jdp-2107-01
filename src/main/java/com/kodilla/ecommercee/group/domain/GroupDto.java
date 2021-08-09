package com.kodilla.ecommercee.group.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Long id;
    private String name;
    private List<Long> productsIds;
}
