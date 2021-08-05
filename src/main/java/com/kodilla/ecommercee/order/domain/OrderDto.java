package com.kodilla.ecommercee.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDate dateOfOrder;
    private BigDecimal price;
    private List<Long> productsIds;
    private Long userId;
}
