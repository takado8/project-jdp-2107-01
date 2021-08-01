package com.kodilla.ecommercee.product.service;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDbService {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Optional<Product> getProduct(Long productId) {
        return productDao.findById(productId);
    }
}
