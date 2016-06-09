package com.example.adam.finalproject01.GameLogic.MazeLogic.Events.RiddleEvents;

import android.text.Editable;
import android.widget.TextView;

/**
 * Created by Adam on 08/06/16.
 */
public class Caesar extends AnswerTwister
{
    private int shift=2;
    private TextView content;

    public Caesar(TextView content)
    {
        this.content=content;
    }

    public char cipher(char x){
        //String s = "";
       // for(int x = 0; x < len; x++){
        incrementShift();
            char c =(char)( x + shift);
            if (c > 'z')
                return(char)(x - (26-shift));
            else
                return(char)(x + shift);
       // }
    }

    private void incrementShift()
    {
        shift++;
        if (shift>=26) shift-=26;
    }

    public int getShift()
    {
        return shift;
    }


       // Caesar c= new Caesar();

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        // if (!(s.length() <= 1))
        if(before<count) {
            if (s.length() == 1) content.setText("" + cipher(s.charAt(
                    s.length() - 1)));
            if (s.length() > 1)
                content.setText(content.getText().toString() + cipher(s.charAt(
                                s.length() - 1))
                );
        }
        else
        {
            if (content.getText().length()>=1)content.setText(content.getText().subSequence(0,content.getText().length()-1));
            else content.setText("");
        }
        //}
        //else content.setText(s.charAt(0)+c.cipher(s.charAt(0),3));
        // else content.setText(s);
    }

        @Override
        public void afterTextChanged(Editable s) {

    }

    @Override
    public String twistAnswer(String answer)
    {
        String output="";

        for (int i=0;i<answer.length();i++)output+=cipher(answer.charAt(i));

        return output;
    }
}
