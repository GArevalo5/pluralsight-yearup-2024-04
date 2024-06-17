package com.pluralsight;

import com.pluralsight.application.IoCContainer;
import com.pluralsight.services.ActorsDao;
import com.pluralsight.services.mysql.MySqlActorsDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class Demo
{
    public static void main(String[] args)
    {
        IoCContainer ioc = new IoCContainer();
        ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");

        var dataSource = (DataSource)ioc.get("dataSource");
        var dao = (ActorsDao)ioc.get("actorsDao");

//        var dao = new MySqlActorsDao(dataSource);

        var age = 21.5;
        var actors = dao.getAll();

        actors.forEach(actor -> {
            System.out.println(actor.getFirstName());
        });

        System.out.println(dataSource);
    }
}
