package com.pluralsight.controllers;

import com.pluralsight.controllers.exceptions.InvalidCustomerException;
import com.pluralsight.models.Customer;
import com.pluralsight.services.CustomersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController
{
    private CustomersDao customersDao;

    @Autowired
    public CustomersController(CustomersDao customersDao)
    {
        this.customersDao = customersDao;
    }

    @GetMapping("")
    public List<Customer> search(@RequestParam(defaultValue = "", name = "name") String companyName,@RequestParam(defaultValue = "") String country)
    {
        return customersDao.searchCustomers(companyName, country);
    }

    @GetMapping("{customerId}")
    public Customer getCustomerById(@PathVariable String customerId)
    {
        return customersDao.getCustomerById(customerId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) throws InvalidCustomerException
    {
        if(customer.getCompanyName() == null || customer.getCompanyName().isBlank() || customer.getCountry().isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer must have a company name");
        }
        try
        {
            // add the new customer to the database
            var newCustomer = customersDao.addCustomer(customer);
            return newCustomer;
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "OOPS... Our bad. Try again later.");
        }
    }

    @PutMapping("{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable String customerId, @RequestBody Customer customer)
    {
        customersDao.updateCustomer(customerId, customer);
    }

    @DeleteMapping("{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String customerId)
    {
        customersDao.deleteCustomer(customerId);
    }
}
