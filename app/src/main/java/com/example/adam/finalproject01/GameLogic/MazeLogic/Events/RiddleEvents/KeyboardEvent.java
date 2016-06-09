package com.example.adam.finalproject01.GameLogic.MazeLogic.Events.RiddleEvents;

import android.text.TextWatcher;

import com.example.adam.finalproject01.Main;

/**
 * Created by Adam on 09/06/16.
 */
public class KeyboardEvent extends RiddleEvent
{
    private TextWatcher tw;


    private AnswerTwister at;

    public KeyboardEvent(String desc, Main m, AnswerTwister at) {
        super(desc, m);
        this.at=at;
        this.rightAnswer=at.twistAnswer(rightAnswer);
    }

    public String twistAnswer(String a)
    {
        return at.twistAnswer(a);
    }




}
