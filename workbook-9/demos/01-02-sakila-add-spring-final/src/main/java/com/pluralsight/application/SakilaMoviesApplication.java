package com.pluralsight.application;

import com.pluralsight.controllers.ActorsController;
import com.pluralsight.models.Actor;
import com.pluralsight.views.ActorRowView;
import com.pluralsight.views.HomeView;

import java.util.List;

public class SakilaMoviesApplication
{
    ActorsController actorsController;

    public SakilaMoviesApplication(ActorsController actorsController)
    {
        this.actorsController = actorsController;
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
        List<Actor> actors = actorsController.findAllActors();

        // call the ActorListView and pass in the returned list
        view.displayActors(actors);
    }

}
