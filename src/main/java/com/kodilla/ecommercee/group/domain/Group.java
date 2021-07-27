package com.kodilla.ecommercee.group.domain;

import com.kodilla.ecommercee.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_group")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Group {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "GROUPCOL")
    private String groupcol;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "productGroup",
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();
}
