package com.pluralsight.controllers;

// all requests related to Categories will
// be handled by THIS controller

import com.pluralsight.models.Category;
import com.pluralsight.services.CategoriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesController
{
    private CategoriesDao categoriesDao;

    @Autowired
    public CategoriesController(CategoriesDao categoriesDao)
    {
        this.categoriesDao = categoriesDao;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories()
    {
        return categoriesDao.getAll();
    }

    @GetMapping("/categories/{id}")
    public Category getById(@PathVariable int id)
    {
        return categoriesDao.getById(id);
    }
}
