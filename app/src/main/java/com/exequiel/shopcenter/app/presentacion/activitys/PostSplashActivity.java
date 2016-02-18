package com.exequiel.shopcenter.app.presentacion.activitys;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.framework.data.local.helper.DBHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PostSplashActivity extends OrmLiteBaseActivity<DBHelper> {

    private static final long SPLASH_SCREEN_DELAY = 2000;

    @InjectView(R.id.activity_splash_logan)
    public ImageView imageViewSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        presentarSplash();
    }


    private void presentarSplash(){
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //overridePendingTransition(R.anim.left_in, R.anim.left_out);
                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
