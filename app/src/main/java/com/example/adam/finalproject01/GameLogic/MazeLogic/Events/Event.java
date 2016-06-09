package com.example.adam.finalproject01.GameLogic.MazeLogic.Events;

import com.example.adam.finalproject01.Main;

import java.util.Observable;

/**
 * Created by Adam on 26/05/16.
 */
public abstract class Event extends Observable
{
    protected String description;
    protected Boolean isFinished =false;
    protected Main m;

    public Event(String description, Main m)
    {
        this.m=m;

        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public abstract void prepareEvent();


    public void startEvent()
    {
        if (isFinished!=true) prepareEvent();
    }

    public void finish()
    {
        isFinished =true;
        description="you have been there";
        //setChanged();
       // notifyObservers(true);


      //  m.buttonVisibility(true);
    }



}
