package com.kodilla.ecommercee.group.domain;

import com.kodilla.ecommercee.product.domain.Product;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PRODUCT_GROUPS")
public class Group {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupId",
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

//    public Group(@NotNull String name, List<Product> products) {
//        this.name = name;
//        this.products = products;
//    }
}
