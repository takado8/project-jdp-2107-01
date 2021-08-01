package com.kodilla.ecommercee.product.service;

import com.kodilla.ecommercee.product.controller.ProductNotFoundException;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProduct(Long id) throws ProductNotFoundException {
        return productDao.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product order) {
        return productDao.save(order);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }

}
