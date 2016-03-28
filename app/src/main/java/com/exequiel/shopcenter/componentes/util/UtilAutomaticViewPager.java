package com.exequiel.shopcenter.componentes.util;

import android.os.Handler;
import android.support.v4.view.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by exequiel on 27/03/2016.
 */
public class UtilAutomaticViewPager {


    public static void iniciarMovimiento(final ViewPager viewPager){
/*
        final Handler handler = new Handler();

        final int currentItem = viewPager.getCurrentItem();

        final Runnable Update = new Runnable() {

            public void run() {
                if (currentItem == 3) {
                    currentItem = 0;
                }
                viewPager.setCurrentItem(currentItem++, true);
            }
        };

        final Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 3500);*/

    }

}
