package com.example.adam.finalproject01.GameLogic.MazeLogic.Events.RiddleEvents;

import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.Event;
import com.example.adam.finalproject01.Main;

/**
 * Created by Adam on 26/05/16.
 */
public class RiddleEvent extends Event
{
    protected String rightAnswer;

    public RiddleEvent(String desc,  Main m)
    {
        super(desc, m);
        setAnswerAndDesc();

    }

    public Boolean compare(String answer)
    {
        if (rightAnswer.equals(answer.toLowerCase())) finish();
        return false;
    }

    private void setAnswerAndDesc()
    {
        String hold;
        for (int i=0;i<description.length();i++)
        {
            if (description.charAt(i)=='$')
            {
                hold=description.substring(0,i);
                rightAnswer=description.substring(i+1,description.length()).toLowerCase();
                description=hold;
                break;
            }

        }
    }

    public String getRightAnswer()
    {
        return this.rightAnswer;
    }


    @Override
    public void prepareEvent() {

    }


}
