package com.kodilla.ecommercee.product.repository;

import com.kodilla.ecommercee.product.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductDao extends CrudRepository<Product, Long> {
}
