package com.kodilla.ecommercee.cart.domain;

import com.kodilla.ecommercee.product.domain.Product;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CARTS")
public final class Cart {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_CARTS_PRODUCTS",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> productList = new ArrayList<>();
}
