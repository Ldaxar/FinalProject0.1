package com.example.adam.finalproject01.GameLogic.MazeLogic.Events;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;

/**
 * Created by Adam on 27/05/16.
 */
public class MagnometricEvent extends SensorEvent
{
    public MagnometricEvent(String description, Integer sensorType, Context mContext) {
        super(description, sensorType, mContext);
    }

    @Override
    public void run()
    {
        mHandlerThread = new HandlerThread("AccelerometerLogListener");
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

                    if (event.values[0]>35)
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
