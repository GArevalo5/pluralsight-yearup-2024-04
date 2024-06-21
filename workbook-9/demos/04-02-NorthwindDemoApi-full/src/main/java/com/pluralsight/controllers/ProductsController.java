package com.pluralsight.controllers;

import com.pluralsight.models.Product;
import com.pluralsight.services.ProductsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController
{
    private ProductsDao productsDao;

    @Autowired
    public ProductsController(ProductsDao productsDao)
    {
        this.productsDao = productsDao;
    }

    @GetMapping("")
    public List<Product> getProducts(@RequestParam(defaultValue = "1", name = "cat") int categoryId)
    {
        return productsDao.getProductByCategoryId(categoryId);
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable int id)
    {
        return productsDao.getProductById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product)
    {
        return productsDao.addProduct(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody Product product)
    {
        productsDao.updateProduct(id, product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id)
    {
        productsDao.deleteProduct(id);
    }
}
