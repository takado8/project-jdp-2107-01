package com.kodilla.ecommercee.order.domain;


import com.kodilla.ecommercee.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public final class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "VALUE")
    private double value;

    @NotNull
    @Column(name = "DATE")
    private LocalDate dateOfOrder;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_ORDER_PRODUCTS",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> productList = new ArrayList<>();

    public Order(@NotNull double value, @NotNull LocalDate dateOfOrder) {
        this.value = value;
        this.dateOfOrder = dateOfOrder;
    }
}
