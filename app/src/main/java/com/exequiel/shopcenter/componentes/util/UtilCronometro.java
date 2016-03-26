package com.exequiel.shopcenter.componentes.util;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by exequiel on 24/03/2016.
 */
public class UtilCronometro {

    private TextView timerValue;

    private long startTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private boolean iniciado;

    private Handler customHandler = new Handler();

    public UtilCronometro(TextView timerValue){
        this.timerValue=timerValue;
    }

    public void startCronometro(){
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
        iniciado=true;
    }

    public void reinciarCronometro(){
        startTime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;
        startCronometro();
    }

    public void stopCronometro(){
        if (iniciado){
            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);
            iniciado=false;
        }
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };

}
