package com.pluralsight.services;

import com.pluralsight.models.Actor;

import java.util.List;

public interface ActorsDao
{
    // define all CRUD
    List<Actor> getAll();
    List<Actor> searchByName(String firstName, String lastName);
    Actor getById(int id);
    Actor addActor(Actor actor);
    void updateActor(Actor actor);
    void deleteActor(int actorId);
}
