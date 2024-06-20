package com.pluralsight.services;

import com.pluralsight.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlProductsDao implements ProductsDao {

    private DataSource dataSource;

    @Autowired
    public MySqlProductsDao(DataSource dataSource)
    {
      this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() {

        List<Product> products = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){

            String sql = """
                    Select ProductID
                    , ProductName
                    ,SupplierID
                    ,CategoryID
                    ,QuantityPerUnit
                    ,UnitPrice
                    ,UnitsInStock
                    ,UnitsOnOrder
                    ,ReorderLevel
                    ,Discontinued
                    FROM products;
                    """;

            Statement statement = connection.createStatement();

            ResultSet row = statement.executeQuery(sql);

            while(row.next()){
                Product product = mapRowsToProducts(row);

                products.add(product);
            }

        } catch (SQLException e) {

        }

        return products;
    }

    @Override
    public Product getById(int Id) {
        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    Select ProductID
                    , ProductName
                    ,SupplierID
                    ,CategoryID
                    ,QuantityPerUnit
                    ,UnitPrice
                    ,UnitsInStock
                    ,UnitsOnOrder
                    ,ReorderLevel
                    ,Discontinued
                    FROM products
                    WHERE ProductID = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,Id);

            ResultSet row = statement.executeQuery();

            if (row.next())
            {
                return mapRowsToProducts(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Product mapRowsToProducts(ResultSet row) throws SQLException
    {
        int productId = row.getInt("ProductID");
        String productName = row.getString("ProductName");
        int supplierId = row.getInt("SupplierID");
        int categoryId = row.getInt("CategoryID");
        String quantityPerUnit = row.getString("QuantityPerUnit");
        double unitPrice = row.getDouble("UnitPrice");
        int unitsInStock = row.getInt("UnitsInStock");
        int unitsOnOrder = row.getInt("UnitsOnOrder");
        int reOrderLevel = row.getInt("ReorderLevel");
        int discontinued = row.getInt("Discontinued");

        return new Product(productId,productName,supplierId,categoryId,quantityPerUnit,unitPrice,unitsInStock
                ,unitsOnOrder, reOrderLevel, discontinued);
    }
}
