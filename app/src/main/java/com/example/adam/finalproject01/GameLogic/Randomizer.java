package com.example.adam.finalproject01.GameLogic;

import java.util.Random;

/**
 * Created by Adam on 26/05/16.
 */
public class Randomizer
{
    private int i;
    public int rand(int r)
    {
        Random max1 = new Random();
        return max1.nextInt(r)+1;
    }

    public String randStr()
    {
        String returnStr="";
        for (i=0;i<rand(10);i++)
        {
            returnStr=returnStr+" "+rand(10);
        }
        return returnStr;
    }
}
