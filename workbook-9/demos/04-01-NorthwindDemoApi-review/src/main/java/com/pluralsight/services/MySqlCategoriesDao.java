package com.pluralsight.services;

import com.pluralsight.models.Category;
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

    public Category mapRowToCategory(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("CategoryId");
        String categoryName = row.getString("CategoryName");
        String description = row.getString("Description");

        return new Category(categoryId, categoryName, description);
    }
}
