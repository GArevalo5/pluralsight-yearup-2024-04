package com.pluralsight;

import com.pluralsight.application.IoCContainer;
import com.pluralsight.application.SakilaMoviesApplication;

public class Main
{
    public static void main(String[] args)
    {
        IoCContainer config = new IoCContainer();

        // the Application needs controllers and views
        SakilaMoviesApplication app = new SakilaMoviesApplication(config);
        app.run();

    }



}