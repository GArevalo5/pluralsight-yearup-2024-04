package com.pluralsight.application;

import com.pluralsight.controllers.ActorsController;
import com.pluralsight.services.ActorsDao;
import com.pluralsight.services.mysql.MySqlActorsDao;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class IoCContainer
{
    Map<String, Object> components = new HashMap<>();

    public IoCContainer()
    {
        var dataSource = buildDataSource();
        var actorsDao = new MySqlActorsDao(dataSource);
        var actorsController = new ActorsController(actorsDao);

        components.put("dataSource", dataSource);
        components.put("actorsDao", actorsDao);
        components.put("actorsController", actorsController);
    }

    private DataSource buildDataSource()
    {
        try (FileInputStream stream = new FileInputStream("src/main/resources/config.properties"))
        {
            // first get the variables from the properties file
            Properties properties = new Properties();
            properties.load(stream);

            String connectionString = properties.getProperty("db.connectionString");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // build a BasicDataSource object
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(connectionString);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            return dataSource;

        }
        catch (IOException e)
        {
        }

        return null;
    }

    public Object get(String key)
    {
        return components.get(key);
    }
}
