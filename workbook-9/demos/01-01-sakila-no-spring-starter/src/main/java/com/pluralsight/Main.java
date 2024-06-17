package com.pluralsight;

import com.pluralsight.application.iocContainer;
import com.pluralsight.application.SakilaMoviesApplication;

public class Main
{
    public static void main(String[] args)
    {
        iocContainer config = new iocContainer();

        // the Application needs controllers and views
        SakilaMoviesApplication app = new SakilaMoviesApplication(config);
        app.run();

    }



}