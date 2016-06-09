package com.example.adam.finalproject01;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.finalproject01.GameLogic.Characters.Player;
import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.Event;
import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.RiddleEvents.Caesar;
import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.RiddleEvents.KeyboardEvent;
import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.RiddleEvents.RiddleEvent;
import com.example.adam.finalproject01.GameLogic.MazeLogic.PlayerMaze;

import java.io.BufferedReader;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Main extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = false;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    private View mContentView;
    private TextView backGround;
    private View mControlsView;
    private TextView container;
    private Button[] directions;
    private boolean mVisible;
    private PlayerMaze tm;
    private Player p;
    private int i;
    private EditText editText;
    private Main m=this;
    BufferedReader buf ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        p=new Player(0,0,new PlayerMaze(10,10,this));
        // set gui variables
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        backGround = (TextView)findViewById(R.id.fullscreen_content);
        container= (TextView)findViewById(R.id.textView);
        container.setMovementMethod(new ScrollingMovementMethod());
        editText =(EditText)findViewById(R.id.input);
        backGround.setText("");
        backGround.setVisibility(View.INVISIBLE);
        //Caesar c=new Caesar(backGround);
        editText.setVisibility(View.INVISIBLE);
        //editText.addTextChangedListener(c);

        setButtons();


        //Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();


        // Set up the user interaction to manually show or hide the system UI.
        /*mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  toggle();
            }
        });*/
        //mContentView.set

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    //set buttons functionality
    private void setButtons()
    {
        directions= new Button[4];
        directions[0]=(Button)findViewById(R.id.West);
        directions[1]=(Button)findViewById(R.id.North);
        directions[2]=(Button)findViewById(R.id.East);
        directions[3]=(Button)findViewById(R.id.South);
        final Main m=this;
        p.getRoom(3,5).setE(new KeyboardEvent(this.getApplicationContext().getString(R.string.t1)
                , this, new Caesar(editText)));
        directions[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(p.moveWest())
                {
                    setCurrentEvent();
                   // hideButtons();

                    Toast.makeText(m, "Moving westh"+ p.getX()+"h"+p.getY(), Toast.LENGTH_SHORT).show();

                }
                else Toast.makeText(m, "You can't go there", Toast.LENGTH_SHORT).show();
                //directions[0].setVisibility(View.INVISIBLE);

            }
        });
        directions[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p.moveNorth())
                {
                    setCurrentEvent();
                   // hideButtons();

                    Toast.makeText(m, "Moving north", Toast.LENGTH_SHORT).show();

                }
                else Toast.makeText(m, "You can't go there"+ p.getX()+"h"+p.getY(), Toast.LENGTH_SHORT).show();


            }
        });
        directions[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p.moveEast()) {
                    // p.getCurrentRoom().setE(new LightEvent(getString(R.string.magnometric).toString(), TYPE_LIGHT, m));

                    setCurrentEvent();
                  //  hideButtons();

                    Toast.makeText(m, "Moving east", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(m, "You can't go there" + p.getX() + "h" + p.getY(), Toast.LENGTH_SHORT).show();

                //directions[0].setVisibility(View.VISIBLE);
            }
        });
        directions[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //boolean flag=false;
                if (directions[3].getText().equals("done")) {
                 //   showButtons();
                    setCurrentEvent();
                } else {
                    if (p.moveSouth()) {
                     //   hideButtons();
                        setCurrentEvent();
                        //directions[3].setText("done;");
                        Toast.makeText(m, "Moving south" + p.getX() + "h" + p.getY(), Toast.LENGTH_SHORT).show();

                    } else Toast.makeText(m, "You can't go there", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }




        public void hideButtons()
        {




                int visibility=View.INVISIBLE;
                directions[3].setText("done");

            for (i=0;i<directions.length-1;i++) directions[i].setVisibility(visibility);


        }

    public void showButtons()
    {

        int visibility=View.VISIBLE;
        directions[3].setText("east");


        for (i=0;i<directions.length-1;i++) directions[i].setVisibility(visibility);


    }
        //prepare current event to be executed by applicatio or finished
        private void setCurrentEvent()
        {
            Event e= p.getCurrentRoom().getE();
            container.setText(e.getDescription());
            if(!p.getCurrentRoom().getIsExit()) {
                if (e.getClass().equals(RiddleEvent.class) && e.getIsFinished() == false) {


                    RiddleEvent re = (RiddleEvent) e;
                    editText.setVisibility(View.VISIBLE);
                    if (re.compare(editText.getText().toString())) {
                        e.finish();
                        // container.setText("finished");
                        editText.setVisibility(View.INVISIBLE);
                    }
                } else if (e.getClass().equals(KeyboardEvent.class) && e.getIsFinished() == false) {
                    KeyboardEvent ke = (KeyboardEvent) e;
                    editText.setVisibility(View.VISIBLE);
                    Toast.makeText(m, ke.twistAnswer(editText.getText().toString()), Toast.LENGTH_SHORT).show();
                    if (ke.compare(ke.twistAnswer(editText.getText().toString()))) {
                        e.finish();
                        // container.setText("finished");
                        editText.setVisibility(View.INVISIBLE);
                    }

                } else editText.setVisibility(View.INVISIBLE);
                if (e.getIsFinished() == false) {
                    hideButtons();
                    e.startEvent();
                } else showButtons();
                //buttonVisibility(e.getIsFinished());
            }
            else
            {
                hideButtons();
                directions[3].setVisibility(View.INVISIBLE);
            }

        }

/*
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Toast.makeText(this, keyCode, Toast.LENGTH_SHORT).show();
        backGround.setText(keyCode);
        return true;
    }
*/





}





