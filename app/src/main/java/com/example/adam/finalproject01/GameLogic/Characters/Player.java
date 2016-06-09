package com.example.adam.finalproject01.GameLogic.Characters;

import com.example.adam.finalproject01.GameLogic.MazeLogic.PlayerMaze;
import com.example.adam.finalproject01.GameLogic.MazeLogic.Room;

/**
 * Created by Adam on 26/05/16.
 */
public class Player extends Character
{
    private Integer x,y;
    private final PlayerMaze pm;

    public Player(Integer y, Integer x, PlayerMaze pm)
    {
        super(100);
        this.y = y;
        this.x = x;
        this.pm=pm;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Boolean moveWest ()
    {
        Integer attempt=x-1;
        if (pm.west(attempt)&& pm.getRoom(x,y).getE().getIsFinished()==true)
        {
            x=attempt;
            return true;
        }
        else return false;
    }

    public Boolean moveNorth ()
    {

        Integer attempt=y-1;

        if (pm.north(attempt)&& pm.getRoom(x,y).getE().getIsFinished()==true)
        {
            y=attempt;
            return true;
        }
        else return false;
    }
    public Boolean moveEast ()
    {
        Integer attempt=x+1;
        if (x==0 && y==0)
        {
            x=1;
            return true;
        }
        if (pm.east(attempt)&& pm.getRoom(x,y).getE().getIsFinished()==true)
        {
            x=attempt;
            return true;
        }
        else return false;
    }
    public Boolean moveSouth ()
    {
        Integer attempt=y+1;
        if (x==0 && y==0)
        {
            y=1;
            return true;
        }
        if (pm.south(attempt) && pm.getRoom(x,y).getE().getIsFinished()==true )
        {
            y=attempt;
            return true;
        }
        else return false;
    }



    public Room getRoom(Integer x, Integer y)
    {
        return pm.getRoom(x,y);
    }

    public Room getCurrentRoom()
    {
        return pm.getRoom(x,y);
    }




}
