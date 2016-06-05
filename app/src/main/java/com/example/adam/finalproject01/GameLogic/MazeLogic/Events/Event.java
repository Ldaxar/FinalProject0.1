package com.example.adam.finalproject01.GameLogic.MazeLogic.Events;

/**
 * Created by Adam on 26/05/16.
 */
public abstract class Event
{
    protected String description;
    protected Boolean isFinished =false;

    Event(String description)
    {
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
    }



}
