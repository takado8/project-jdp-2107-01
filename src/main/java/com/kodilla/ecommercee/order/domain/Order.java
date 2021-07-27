package com.kodilla.ecommercee.order.domain;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "PRICE")
    private double price;

    @NotNull
    @Column(name = "DATE")
    private LocalDate dateOfOrder;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();
}
