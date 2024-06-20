package com.pluralsight.controllers;

import com.pluralsight.models.Customer;
import com.pluralsight.models.Product;
import com.pluralsight.services.ProductsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product)
    {
        var newProduct = productsDao.addProduct(product);
        return newProduct;
    }

    @PutMapping("products/{productId}")
    public void updateProduct(@PathVariable int productId, @RequestBody Product product)
    {
        productsDao.updateProduct(productId,product);
    }

    @DeleteMapping("/products/{productId")
    public void deleteCustomer(@PathVariable int productId)
    {
        productsDao.deleteProduct(productId);
    }
}
