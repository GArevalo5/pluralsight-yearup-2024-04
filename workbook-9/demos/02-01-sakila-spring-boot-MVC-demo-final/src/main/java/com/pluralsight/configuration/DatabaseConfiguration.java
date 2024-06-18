package com.pluralsight.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration
{
    private DataSource dataSource;

    @Bean
    public DataSource getDataSource()
    {
        return dataSource;
    }

    public DatabaseConfiguration(@Value("${db.connectionString}") String url,
                                 @Value("${db.username}") String userName,
                                 @Value("${db.password}") String password)
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        this.dataSource = dataSource;
    }

}
