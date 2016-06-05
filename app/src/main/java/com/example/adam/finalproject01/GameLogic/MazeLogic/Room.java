package com.example.adam.finalproject01.GameLogic.MazeLogic;

import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.Event;

/**
 * Created by Adam on 26/05/16.
 */
public class Room
{
    private final Boolean exist;
    private final Boolean isExit;
    private Boolean isLocked;
    private Event e;


    public Room(Boolean exist, Boolean isExit, Event e) {
        this.exist = exist;
        if (exist==false)
        {
            this.isExit=false;
            return;
        }
        else
        {
            this.e=e;
            this.isExit=isExit;
        }

    }

    public Boolean getExist() {
        return exist;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void lock(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Event getE() {
        return e;
    }
}
