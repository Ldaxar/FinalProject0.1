package com.example.adam.finalproject01.GameLogic.MazeLogic.Events.RiddleEvents;

import android.text.TextWatcher;

/**
 * Created by Adam on 09/06/16.
 */
public abstract class AnswerTwister implements TextWatcher
{
    public abstract String twistAnswer(String answer);
}
