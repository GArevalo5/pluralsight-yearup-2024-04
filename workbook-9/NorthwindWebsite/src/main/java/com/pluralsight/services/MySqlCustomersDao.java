package com.pluralsight.services;

import com.pluralsight.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCustomersDao implements CustomersDao
{
    private DataSource dataSource;

    @Autowired
    public MySqlCustomersDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public List<Customer> searchCustomers(String customerName, String searchCountry)
    {
        List<Customer> customers = new ArrayList<>();

        try(Connection con = dataSource.getConnection())
        {
            String sql = """
                    SELECT CustomerId
                        , CompanyName
                        , ContactName
                        , Address
                        , City
                        , Region AS State
                        , PostalCode as Zip
                        , Country
                    FROM customers
                    WHERE customers.CompanyName LIKE ?
                        AND Country LIKE ?
                    """;
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + customerName + "%");
            statement.setString(2, "%" + searchCountry + "%");

            ResultSet row = statement.executeQuery();

            while(row.next())
            {
                Customer customer = mapRowToCustomer(row);
                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    public Customer mapRowToCustomer(ResultSet row) throws SQLException
    {

        String customerId = row.getString("CustomerId");
        String companyName = row.getString("CompanyName");
        String contactName = row.getString("ContactName");
        String address = row.getString("Address");
        String city = row.getString("City");
        String state = row.getString("State");
        String zip = row.getString("Zip");
        String country = row.getString("Country");

        return new Customer(customerId, companyName, contactName, address, city, state, zip, country);
    }
}
