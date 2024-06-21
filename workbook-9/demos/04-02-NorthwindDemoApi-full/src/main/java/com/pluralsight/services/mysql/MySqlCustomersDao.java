package com.pluralsight.services.mysql;

import com.pluralsight.models.Customer;
import com.pluralsight.services.CustomersDao;
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

    public Customer getCustomerById(String searchCustomerId)
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
                    WHERE customerId = ?
                    """;
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, searchCustomerId);

            ResultSet row = statement.executeQuery();

            if(row.next())
            {
                Customer customer = mapRowToCustomer(row);
                return customer;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Customer addCustomer(Customer customer)
    {
        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    INSERT INTO customers (CustomerId, CompanyName, ContactName, Address, City, Region, PostalCode, Country)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getCompanyName());
            statement.setString(3, customer.getContactName());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getCity());
            statement.setString(6, customer.getState());
            statement.setString(7, customer.getZip());
            statement.setString(8, customer.getCountry());

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
        }

        return getCustomerById(customer.getCustomerId());
    }

    @Override
    public void updateCustomer(String customerId, Customer customer)
    {
        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    UPDATE Customers
                    SET CompanyName = ?
                        , ContactName = ?
                        , Address = ?
                        , City = ?
                        , Region = ?
                        , PostalCode = ?
                        , Country = ?
                    WHERE CustomerId = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getCompanyName());
            statement.setString(2, customer.getContactName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getCity());
            statement.setString(5, customer.getState());
            statement.setString(6, customer.getZip());
            statement.setString(7, customer.getCountry());
            statement.setString(8, customerId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
        }
    }

    @Override
    public void deleteCustomer(String customerId)
    {
        try(Connection connection = dataSource.getConnection())
        {
            String sql = "DELETE FROM Customers WHERE CustomerId = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
        }
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
