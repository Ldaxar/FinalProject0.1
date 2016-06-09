package com.example.adam.finalproject01.GameLogic.MazeLogic.Events.SensorEvents;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Vibrator;

import com.example.adam.finalproject01.GameLogic.MazeLogic.Events.Event;
import com.example.adam.finalproject01.Main;

/**
 * Created by Adam on 27/05/16.
 */
public abstract class  SensorEvent extends Event implements Runnable
{

    protected final Context mContext;
    protected HandlerThread mHandlerThread;
    protected Handler handler;
    protected Sensor mSensor;
    protected SensorManager mSensorManager;
    protected SensorEventListener mListener;
    protected Vibrator mVibrator;

    SensorEvent(String description, Integer sensorType, Main m) {
        super(description, m);
        this.mContext = m.getApplicationContext();
        mSensorManager = (SensorManager) mContext.getSystemService(mContext.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(sensorType);
        mVibrator = (Vibrator)mContext.getSystemService(mContext.VIBRATOR_SERVICE);


    }



    protected void cleanThread()
    {

        //Unregister the listener
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(mListener);
           // mVibrator.cancel();
        }

        if (mHandlerThread.isAlive())
        {
            mHandlerThread.quitSafely();
            finish();


        }


    }

}
