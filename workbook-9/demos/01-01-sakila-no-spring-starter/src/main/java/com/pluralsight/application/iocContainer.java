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

public class iocContainer
{
    Map<String,Object> components = new HashMap<>();

    public iocContainer() {
        var dataSource = buildDataSource();
        var actorDao = new MySqlActorsDao(dataSource);
        var actorsController = new ActorsController(actorsDao);
    }

    // datasources
    public final DataSource dataSource = buildDataSource();

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

    // daos
    public ActorsDao actorsDao = new MySqlActorsDao(dataSource);

    // controllers
    public ActorsController actorsController = new ActorsController(actorsDao);

}
