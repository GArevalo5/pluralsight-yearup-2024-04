package com.pluralsight.application;

import com.pluralsight.models.Actor;
import com.pluralsight.views.ActorRowView;
import com.pluralsight.views.HomeView;

import java.util.List;

public class SakilaMoviesApplication
{
    private final iocContainer config;

    public SakilaMoviesApplication(iocContainer config)
    {
        this.config = config;
    }


    public void run()
    {
        while(true)
        {
            int selection = displayHomeSelection();

            switch (selection)
            {
                case 1 -> displayActorList();
                case 0 -> System.exit(0);
            }
        }
    }

    private int displayHomeSelection()
    {
        var view = new HomeView();
        return view.getUserSelection();
    }

    private void displayActorList()
    {
        var view = new ActorRowView();

        // go to the Actors controller and get a list of actors
        List<Actor> actors = config.actorsController.findAllActors();

        // call the ActorListView and pass in the returned list
        view.displayActors(actors);
    }

}
