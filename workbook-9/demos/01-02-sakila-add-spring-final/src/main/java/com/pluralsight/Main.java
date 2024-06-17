package com.pluralsight;

import com.pluralsight.application.SakilaMoviesApplication;
import com.pluralsight.controllers.ActorsController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");
        var actorsController = context.getBean(ActorsController.class);

        // the Application needs controllers and views
        SakilaMoviesApplication app = new SakilaMoviesApplication(actorsController);
        app.run();

    }



}