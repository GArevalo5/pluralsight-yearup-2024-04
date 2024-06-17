package com.pluralsight.controllers;

import com.pluralsight.models.Actor;
import com.pluralsight.services.ActorsDao;

import java.util.List;

public class ActorsController
{
    // controller dependencies
    private ActorsDao actorsDao;

    // constructor dependency injection
    public ActorsController(ActorsDao actorsDao)
    {
        this.actorsDao = actorsDao;
    }


    // controller methods

    public List<Actor> findAllActors()
    {
        var actors = actorsDao.getAll();

        return actors;
    }

    public List<Actor> searchByName(String firstName, String lastName)
    {
        return  actorsDao.searchByName(firstName, lastName);
    }

    public Actor searchById(int id)
    {
        return actorsDao.getById(id);
    }

    public Actor addNewActor(Actor actor)
    {
        return actorsDao.addActor(actor);
    }

    public void updateActor(Actor actor)
    {
        actorsDao.updateActor(actor);
    }

    public void deleteActor(int actorId)
    {
        actorsDao.deleteActor(actorId);
    }
}
