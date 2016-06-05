package com.example.adam.finalproject01.GameLogic.MazeLogic;

import android.content.Context;

import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.MagnometricEvent;
import com.example.adam.finalproject01.GameLogic.Randomizer;

import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;

/**
 * Created by Adam on 26/05/16.
 */
public class Maze
{
    protected Room[][] maze;
    protected final Integer height;
    protected final Integer width;
    protected MagnometricEvent me;
    private Context c;
    private Randomizer r= new Randomizer();
    //protected final Integer exit;


    Maze(Integer height, Integer width, Context c)
    {
        this.height=height;
        this.width=width;
        this.c=c;


        maze=new Room[height+1][width+1];
    }

    protected void create ()
    {
        Randomizer r = new Randomizer();
        maze[0][0]= new Room(true, false, me);
        Room currentRoom=maze[1][0];
        int i=1,j=0;
        for (i=0;i<width;i++)
        {
            me= new MagnometricEvent(r.randStr(), TYPE_MAGNETIC_FIELD, c);
            maze[i][j]= new Room(true,false, me);
          //  Log.d("STATE", i+ " "+ j);
            for (j=0;j<height;j++)
            {
                me= new MagnometricEvent(r.randStr(), TYPE_MAGNETIC_FIELD, c);
                maze[i][j]= new Room(true,false, me);
            }
        }
    }

    public Boolean west (Integer currentW)
    {
        if (currentW>=0)return true;
        return false;
    }

    public Boolean east (Integer currentW)
    {
        if (currentW<width && currentW>0) return true;
        return false;
    }

    public Boolean north (Integer currentH)
    {
        if (currentH>=0) return true;
        return false;
    }

    public Boolean south (Integer currentH)
    {
        if (currentH<height) return true;
        return false;
    }


    public Room getRoom(Integer x, Integer y)
    {
        return maze[x][y];
    }
}
