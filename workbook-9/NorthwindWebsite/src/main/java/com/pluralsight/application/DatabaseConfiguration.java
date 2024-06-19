package com.pluralsight.application;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DatabaseConfiguration {
    private DataSource dataSource;

    @Bean
    public DataSource getDataSource()
    {
        return this.dataSource;
    }

    public DatabaseConfiguration(@Value("${db.connectionString}") String url,
                                 @Value("${db.username}") String username,
                                 @Value("${db.password}") String password)
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        this.dataSource = dataSource;
    }
}
