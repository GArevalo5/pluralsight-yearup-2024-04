package com.pluralsight.services;

import com.pluralsight.models.Customer;

import java.util.List;

public interface CustomersDao
{
    List<Customer> searchCustomers(String customerName, String country);
}
