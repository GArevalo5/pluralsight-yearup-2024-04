package com.pluralsight.services;

import com.pluralsight.models.Product;
import java.util.List;

public interface ProductsDao {
    List<Product> getAll();
    Product getById(int productId);
}
