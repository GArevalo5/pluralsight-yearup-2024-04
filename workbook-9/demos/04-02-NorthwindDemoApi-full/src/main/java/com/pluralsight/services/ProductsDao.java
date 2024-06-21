package com.pluralsight.services;

import com.pluralsight.models.Product;

import java.util.List;

public interface ProductsDao
{
    List<Product> getProductByCategoryId(int categoryId);

    Product getProductById(int productId);

    Product addProduct(Product product);

    void updateProduct(int productId, Product product);

    void deleteProduct(int productId);
}
