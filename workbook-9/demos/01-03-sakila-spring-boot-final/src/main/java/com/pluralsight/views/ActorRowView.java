package com.pluralsight.views;

import com.pluralsight.models.Actor;

import java.util.List;

public class ActorRowView extends ViewBase
{
    public void displayActors(List<Actor> actors)
    {
        System.out.println();
        System.out.println("Actors");
        System.out.println("-".repeat(40));


        System.out.printf("%-4s %-15s %s\n", "Id", "First Name", "Last Name");
        System.out.printf("%-4s %-15s %s\n", "-".repeat(4), "-".repeat(15), "-".repeat(19));
        actors.forEach( actor -> {
            System.out.printf("%-4d %-15s %s\n", actor.getActorId(), actor.getFirstName(), actor.getLastName());
        });
    }
}
