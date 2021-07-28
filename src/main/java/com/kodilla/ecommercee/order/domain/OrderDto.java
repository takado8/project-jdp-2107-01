package com.kodilla.ecommercee.order.domain;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.user.domain.User;
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
    private User userId;
    private List<Product> products;
}
