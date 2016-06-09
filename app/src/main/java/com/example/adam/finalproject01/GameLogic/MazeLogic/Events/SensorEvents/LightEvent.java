package com.example.adam.finalproject01.GameLogic.MazeLogic.Events.SensorEvents;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;

import com.example.adam.finalproject01.Main;

/**
 * Created by Adam on 27/05/16.
 */
public class LightEvent extends SensorEvent
{
    public LightEvent(String description, Integer sensorType, Main m) {
        super(description, sensorType, m);
    }

    @Override
    public void run()
    {
        mHandlerThread = new HandlerThread("LightLogListener");
        mHandlerThread.start();
        handler = new Handler(mHandlerThread.getLooper());

        mListener = new SensorEventListener()
        {


            @Override
            public void onSensorChanged(android.hardware.SensorEvent event)
            {

                if (event.sensor==mSensor)
                {
                    int result=(int)event.values[0];

                    if (event.values[0]<5)
                    {

                        mVibrator.vibrate(250);

                        cleanThread();
                    }
                    //ifClicked=false;
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy)
            {

            }
        };

        mSensorManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_FASTEST,
                handler
        );

    }


    @Override
    public void prepareEvent() {
        run();

    }



}