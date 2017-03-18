package uk.ac.napier.millisecondchronometer;

import android.content.Context;

/**
 * Created by Wallentin on 14/03/2017.
 */

// keep track of time, update UI with time has passed since start of chronometer
public class Chronometer implements Runnable
{
    public static final long millisecondsToMinutes = 60000;                                         //60*1000 //static constant //states how many milliseconds are in a minute
    public static final long millisecondsToSeconds = 1000;

    private Context cContext;                                                                       //way to access main activity
    private long cStartTime;                                                                        //keep track of starting time

    private boolean cIsRunning;

    public Chronometer(Context cContext)                                                            //constructor
    {
        this.cContext = cContext;
    }

    // methods to call from main activity
    public void start()
    {
        cStartTime = System.currentTimeMillis();                                                    //captures the base time of device in milliseconds as soon as the chronometer starts running
        cIsRunning = true;
    }

    public void stop()
    {
        cIsRunning = false;
    }

    @Override
    public void run()
    {
        while(cIsRunning)                                                                           //check if chronometer is running
        {
            long since          = System.currentTimeMillis() - cStartTime;                          //how many milliseconds have passed since we started

            int milliseconds    = (int) (since % 1000);
            int seconds         = (int) ((since / millisecondsToSeconds)% 60);
            int minutes         = (int) ((since / millisecondsToMinutes) % 60);                     // improving performance with the created constant

            ((MainActivity)cContext).updateChronometer(String.format("%01d:%02d:%03d", minutes, seconds, milliseconds));
        }
    }
}
