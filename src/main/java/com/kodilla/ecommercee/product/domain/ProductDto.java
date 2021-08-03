package com.kodilla.ecommercee.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long groupId;
    private List<Long> ordersId;
    private List<Long> cartsId;
}