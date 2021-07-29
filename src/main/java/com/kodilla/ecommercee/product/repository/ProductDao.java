package com.kodilla.ecommercee.product.repository;

import com.kodilla.ecommercee.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductDao extends JpaRepository<Product, Long> {
}
