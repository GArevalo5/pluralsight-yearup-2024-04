package com.pluralsight.controllers;

import com.pluralsight.models.Customer;
import com.pluralsight.services.CustomersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId)
    {
        return customersDao.getCustomerById(customerId);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer)
    {
        // add the new customer to the database
        var newCustomer = customersDao.addCustomer(customer);
        return newCustomer;
    }

    @PutMapping("/customers/{customerId}")
    public void updateCustomer(@PathVariable String customerId, @RequestBody Customer customer)
    {
        customersDao.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable String customerId)
    {
        customersDao.deleteCustomer(customerId);
    }
}
