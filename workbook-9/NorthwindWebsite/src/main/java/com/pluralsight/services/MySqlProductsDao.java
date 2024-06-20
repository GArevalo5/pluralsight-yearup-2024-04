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

    @Override
    public Product addProduct(Product product) {

        try(Connection connection = dataSource.getConnection()) {
            String sql = """
                    INSERT INTO products
                    ( ProductID
                    , ProductName
                    ,SupplierID
                    ,CategoryID
                    ,QuantityPerUnit
                    ,UnitPrice
                    ,UnitsInStock
                    ,UnitsOnOrder
                    ,ReorderLevel
                    ,Discontinued)
                    VALUES(?,?,?,?,?,?,?,?,?,?);
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,product.getProductId());
            statement.setString(2, product.getProductName());
            statement.setInt(3, product.getSupplierId());
            statement.setInt(4,product.getCategoryId());
            statement.setString(5, product.getQuantityPerUnit());
            statement.setDouble(6, product.getUnitPrice());
            statement.setInt(7,product.getUnitsInStock());
            statement.setInt(8,product.getUnitsOnOrder());
            statement.setInt(9,product.getDiscontinued());


            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getById(product.getProductId());
    }

    @Override
    public void updateProduct(int productId, Product product)
    {
        try(Connection connection = dataSource.getConnection()) {
            String sql = """
                    UPDATE products
                    SET ProductName = ?
                    , SupplierID = ?
                    , CategoryID = ?
                    , QuantityPerUnit = ?
                    , UnitPrice = ?
                    , UnitsInStock = ?
                    , UnitsOnOrder = ?
                    , ReorderLevel = ?
                    , Discontinued = ?
                    WHERE ProductID = ?
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getSupplierId());
            statement.setInt(3,product.getCategoryId());
            statement.setString(4, product.getQuantityPerUnit());
            statement.setDouble(5, product.getUnitPrice());
            statement.setInt(6,product.getUnitsInStock());
            statement.setInt(7,product.getUnitsOnOrder());
            statement.setInt(8,product.getDiscontinued());
            statement.setInt(9,product.getProductId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteProduct(int productId)
    {
        try(Connection connection = dataSource.getConnection()){
            String sql = """
                    DELETE
                    FROM products
                    WHERE ProductID = ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,productId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
