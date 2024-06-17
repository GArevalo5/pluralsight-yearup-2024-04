package com.pluralsight;

import com.pluralsight.models.Actor;
import com.pluralsight.services.mysql.MySqlActorsDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo
{
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");


        var johnDoe = context.getBean("johnDoe", Actor.class);

        var dataSource = context.getBean(BasicDataSource.class);
        var actorsDao = context.getBean(MySqlActorsDao.class);



//
//        actors.forEach(actor -> {
//            System.out.println(actor.getFirstName());
//        });

        System.out.println(dataSource);
    }
}
