package com.kodilla.ecommercee.product.repository;

import com.kodilla.ecommercee.product.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductDao extends CrudRepository<Product, Long> {

    @Override
    List<Product> findAll();
    List<Product> findAllById(List<Long> id);
    @Override
    List<Product> findAll();
}
