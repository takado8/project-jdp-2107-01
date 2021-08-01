package com.kodilla.ecommercee.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long userId;
    private List<Long> productsId;
}
