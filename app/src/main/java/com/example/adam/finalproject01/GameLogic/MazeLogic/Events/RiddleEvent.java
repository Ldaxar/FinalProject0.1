package com.example.adam.finalproject01.GameLogic.MazeLogic.Events;

/**
 * Created by Adam on 26/05/16.
 */
public class RiddleEvent extends Event
{
    private String rightAnswer;

    RiddleEvent(String desc, String answer)
    {
        super(desc);
        rightAnswer=answer;

    }

    private Boolean compare(String answer)
    {
        if (rightAnswer.equals(answer.toLowerCase())) return true;
        return false;
    }


    @Override
    public void prepareEvent() {

    }


}
