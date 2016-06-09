package com.example.adam.finalproject01.GameLogic.MazeLogic;

import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.Event;
import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.RiddleEvents.RiddleEvent;
import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.SensorEvents.LightEvent;
import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.SensorEvents.MagnometricEvent;
import com.example.adam.finalproject01.GameLogic.Randomizer;
import com.example.adam.finalproject01.Main;
import com.example.adam.finalproject01.R;

import static android.hardware.Sensor.TYPE_LIGHT;
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
    private Main m;
    private Randomizer r= new Randomizer();
    //protected final Integer exit;


    Maze(Integer height, Integer width, Main m)
    {
        this.height=height;
        this.width=width;
        this.m=m;


        maze=new Room[height+1][width+1];
    }

    protected void create ()
    {
        Randomizer r = new Randomizer();
        RiddleEvent re= new RiddleEvent("sndkasnkj", m);
        re.finish();
        maze[0][0]= new Room(true, false, re);
       // Room currentRoom=maze[1][0];
        int i=1,j=0;
        for (i=0;i<width;i++)
        {
            me= new MagnometricEvent(m.getApplicationContext().getString(R.string.empty), TYPE_MAGNETIC_FIELD, m);
            maze[i][j]= new Room(true,false, me);

          //  Log.d("STATE", i+ " "+ j);
            for (j=0;j<height;j++)
            {
                me= new MagnometricEvent(m.getApplicationContext().getString(R.string.empty), TYPE_MAGNETIC_FIELD, m);
                maze[i][j]= new Room(true,false, me);
            }
        }
        setRight();
    }

    private void setRight()
    {
        maze[0][1]= new Room(true,false,me);
        maze[1][1]= new Room(true,false,new LightEvent(m.getApplicationContext().getString(R.string.magnometric).toString(), TYPE_LIGHT, m));
        //---------------------------------Riddles---------------------------------------//
        maze[1][2]=new Room(true,false,new RiddleEvent(m.getApplicationContext().getString(R.string.r1).toString(),m));
        maze[2][2]=new Room(true,false,new RiddleEvent(m.getApplicationContext().getString(R.string.r2).toString(),m));
        maze[3][2]=new Room(true,false,new RiddleEvent(m.getApplicationContext().getString(R.string.r3).toString(),m));
        maze[3][3]=new Room(true,false,new RiddleEvent(m.getApplicationContext().getString(R.string.r4).toString(),m));
        maze[3][4]=new Room(true,false,new RiddleEvent(m.getApplicationContext().getString(R.string.r5).toString(),m));

        maze[4][5]=new Room(true, true, new Event("YOU WON THE GAME",m) {
            @Override
            public void prepareEvent() {

            }
        });
        //maze[1][3]=new Room(true,false,new RiddleEvent(m.getApplicationContext().getString(R.string.magnometric).toString()+'e',m));

        //maze[1][3]= new Room(true,false,new KeyboardEvent(m.getApplicationContext().getString(R.string.magnometric),));

        //  maze[0][2]= new Room(true,false,new KeyboardEvent())
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
