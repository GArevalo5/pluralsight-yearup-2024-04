package com.pluralsight;

import com.pluralsight.application.IoCContainer;
import com.pluralsight.services.ActorsDao;
import com.pluralsight.services.mysql.MySqlActorsDao;

import javax.sql.DataSource;

public class Demo
{
    public static void main(String[] args)
    {
        IoCContainer ioc = new IoCContainer();

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
