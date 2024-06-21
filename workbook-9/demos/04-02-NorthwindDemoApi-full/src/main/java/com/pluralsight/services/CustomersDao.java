package com.pluralsight.services;

import com.pluralsight.models.Customer;

import java.util.List;

public interface CustomersDao
{
    List<Customer> searchCustomers(String customerName, String country);

    Customer getCustomerById(String customerId);

    Customer addCustomer(Customer customer);

    void updateCustomer(String customerId, Customer customer);

    void deleteCustomer(String customerId);
}
