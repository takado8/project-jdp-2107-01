package com.kodilla.ecommercee.product.service;

import com.kodilla.ecommercee.product.controller.ProductNotFoundException;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDbService {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProduct(final Long id) {
        return productDao.findById(id).orElseThrow( () -> new ProductNotFoundException("product of id " + id +" not found"));
    }

    public Product saveProduct(final Product product) {
        return productDao.save(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }

}
