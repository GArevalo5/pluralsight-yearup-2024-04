package com.pluralsight.controllers;

import com.pluralsight.models.Customer;
import com.pluralsight.services.CustomersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomersController
{
    private CustomersDao customersDao;

    @Autowired
    public CustomersController(CustomersDao customersDao)
    {
        this.customersDao = customersDao;
    }

    @GetMapping("/customers")
    public List<Customer> search(@RequestParam(defaultValue = "", name = "name") String companyName,@RequestParam(defaultValue = "") String country)
    {
        return customersDao.searchCustomers(companyName, country);
    }
}
