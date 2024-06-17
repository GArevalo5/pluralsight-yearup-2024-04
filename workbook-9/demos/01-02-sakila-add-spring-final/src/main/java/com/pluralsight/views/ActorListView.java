package com.pluralsight.views;

import com.pluralsight.models.Actor;

import java.util.List;

public class ActorListView extends ViewBase
{
    public void displayActors(List<Actor> actors)
    {
        System.out.println();
        System.out.println("Actors");
        System.out.println("-".repeat(40));


        actors.forEach( actor -> {
            System.out.println("Actor Id:   " + actor.getActorId());
            System.out.println("First Name: " + actor.getFirstName());
            System.out.println("Last Name:  " + actor.getLastName());
            System.out.println("-".repeat(40));
        });
    }
}
