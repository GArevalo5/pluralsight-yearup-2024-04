package com.pluralsight.services.mysql;

import com.pluralsight.models.Category;
import com.pluralsight.services.CategoriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoriesDao implements CategoriesDao
{
    private DataSource dataSource;

    @Autowired
    public MySqlCategoriesDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAll()
    {
        List<Category> categories = new ArrayList<>();

        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    SELECT CategoryId
                        , CategoryName
                        , Description
                    FROM categories;
                    """;

            Statement statement = connection.createStatement();

            ResultSet row = statement.executeQuery(sql);

            while(row.next())
            {
                Category category = mapRowToCategory(row);

                categories.add(category);
            }
        }
        catch (SQLException e)
        {
        }

        return categories;
    }

    @Override
    public Category getById(int id)
    {
        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    SELECT CategoryId
                        , CategoryName
                        , Description
                    FROM categories
                    WHERE CategoryId = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet row = statement.executeQuery();

            if(row.next())
            {
                return mapRowToCategory(row);
            }
        }
        catch (SQLException e)
        {
        }

        return null;
    }

    @Override
    public Category add(Category category)
    {
        int newId = 0;

        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    INSERT INTO categories (CategoryName, Description)
                    VALUES (?, ?);
                    """;
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            newId = generatedKeys.getInt(1);
        }
        catch (SQLException e)
        {
        }

        return getById(newId);
    }

    @Override
    public void update(int categoryId, Category category)
    {
        try(Connection connection = dataSource.getConnection())
        {
            String sql = """
                    UPDATE categories 
                    SET CategoryName = ?
                        , Description = ?
                    WHERE categoryId = ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, categoryId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
        }
    }

    @Override
    public void delete(int categoryId) throws SQLException
    {
        try(Connection connection = dataSource.getConnection())
        {
            String sqlProducts = """
                    UPDATE products 
                    SET CategoryId = NULL
                    WHERE CategoryId = ?;
                    """;
            PreparedStatement statementProducts = connection.prepareStatement(sqlProducts);
            statementProducts.setInt(1, categoryId);
            statementProducts.executeUpdate();


            String sql = """
                    DELETE FROM categories
                    WHERE categoryId = ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw e;
        }
    }

    public Category mapRowToCategory(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("CategoryId");
        String categoryName = row.getString("CategoryName");
        String description = row.getString("Description");

        return new Category(categoryId, categoryName, description);
    }
}
