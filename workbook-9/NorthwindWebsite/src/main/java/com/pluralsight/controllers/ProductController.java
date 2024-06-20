package com.pluralsight.controllers;

import com.pluralsight.models.Product;
import com.pluralsight.services.ProductsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private ProductsDao productsDao;

    @Autowired
    public ProductController(ProductsDao productsDao)
    {
      this.productsDao = productsDao;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return productsDao.getAll();
    }

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable int id)
    {
        return productsDao.getById(id);
    }
}
