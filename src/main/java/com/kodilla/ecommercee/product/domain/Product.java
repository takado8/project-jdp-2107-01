package com.kodilla.ecommercee.product.domain;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "GROUP")
    private Group productGroup;

    @ManyToMany
    @JoinTable(
            name = "PRODUCTS_ORDERS",
            joinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    )
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(mappedBy = "productList")
    private List<Cart> carts = new ArrayList<>();
}
