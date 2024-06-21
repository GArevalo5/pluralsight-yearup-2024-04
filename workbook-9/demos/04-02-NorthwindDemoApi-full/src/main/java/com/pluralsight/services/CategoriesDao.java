package com.pluralsight.services;

import com.pluralsight.models.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoriesDao
{
    List<Category> getAll();
    Category getById(int id);

    Category add(Category category);
    void update(int categoryId, Category category);
    void delete(int categoryId) throws SQLException;
}
