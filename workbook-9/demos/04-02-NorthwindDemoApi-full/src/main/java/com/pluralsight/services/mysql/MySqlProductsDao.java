package com.pluralsight.services.mysql;

import com.pluralsight.models.Product;
import com.pluralsight.services.ProductsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlProductsDao implements ProductsDao
{
    private DataSource dataSource;

    @Autowired
    public MySqlProductsDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getProductByCategoryId(int categoryId)
    {
        List<Product> products = new ArrayList<>();

        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    SELECT ProductID
                        , ProductName
                        , CategoryID
                        , UnitPrice
                    FROM products WHERE CategoryID = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);

            ResultSet row = preparedStatement.executeQuery();

            while(row.next())
            {
                Product product = mapRowToProduct(row);
                products.add(product);
            }
        }
        catch (SQLException e)
        {
        }

        return products;
    }

    @Override
    public Product getProductById(int productId)
    {

        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    SELECT ProductID
                        , ProductName
                        , CategoryID
                        , UnitPrice
                    FROM products WHERE ProductID = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);

            ResultSet row = preparedStatement.executeQuery();

            if(row.next())
            {
                Product product = mapRowToProduct(row);
                return product;
            }
        }
        catch (SQLException e)
        {
        }

        return null;
    }

    @Override
    public Product addProduct(Product product)
    {
        int newId = 0;

        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    INSERT INTO products(ProductName, CategoryID, UnitPrice)
                    VALUES (?, ?, ?);
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getCategoryId());
            preparedStatement.setDouble(3, product.getUnitPrice());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            newId = generatedKeys.getInt(1);
        }
        catch (SQLException e)
        {
        }

        return getProductById(newId);
    }

    @Override
    public void updateProduct(int productId, Product product)
    {

        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    UPDATE products 
                    SET ProductName = ?
                        , CategoryID = ?
                        , UnitPrice = ?
                    WHERE ProductID = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getCategoryId());
            preparedStatement.setDouble(3, product.getUnitPrice());
            preparedStatement.setInt(4, productId);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
        }
    }

    @Override
    public void deleteProduct(int productId)
    {

        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    DELETE FROM products WHERE ProductID = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
        }
    }



    private Product mapRowToProduct(ResultSet row) throws SQLException
    {
        int productId = row.getInt("ProductId");
        String productName = row.getString("ProductName");
        int categoryId = row.getInt("CategoryID");
        double unitPrice = row.getDouble("UnitPrice");

        return new Product(productId, productName, categoryId, unitPrice);
    }
}
