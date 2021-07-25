package com.kodilla.ecommercee.product.domain;

import com.kodilla.ecommercee.cart.domain.Cart;
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
@Table(name = "PRODUCTS")
public final class Product {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "productList")
    private List<Cart> carts = new ArrayList<>();
}
