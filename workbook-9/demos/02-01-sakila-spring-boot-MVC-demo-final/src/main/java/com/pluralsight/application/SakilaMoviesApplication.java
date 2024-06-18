package com.pluralsight.application;

import com.pluralsight.controllers.ActorsController;
import com.pluralsight.models.Actor;
import com.pluralsight.views.ActorRowView;
import com.pluralsight.views.HomeView;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SakilaMoviesApplication  implements CommandLineRunner
{
    ActorsController actorsController;

    public SakilaMoviesApplication(ActorsController actorsController)
    {
        this.actorsController = actorsController;
    }


    @Override
    public void run(String... args) throws Exception
    {
        while (true)
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
