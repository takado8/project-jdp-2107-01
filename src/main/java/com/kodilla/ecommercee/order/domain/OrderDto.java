package com.kodilla.ecommercee.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private double price;
    private LocalDate dateOfOrder;
    private Long userId;
    private List<Long> productsId;
}
