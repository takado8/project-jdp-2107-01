package com.kodilla.ecommercee.cart.domain;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.user.domain.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Cart {
    public Cart(String name, String description, BigDecimal price, User user) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.user = user;
    }

    public Cart(String name, String description, BigDecimal price, User user, List<Product> products) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.user = user;
        this.products = products;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "CARTS_PRODUCTS",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> products = new ArrayList<>();


}
