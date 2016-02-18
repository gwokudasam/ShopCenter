package com.exequiel.shopcenter.app.presentacion.activitys;

import android.content.Intent;
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

public class SplashActivity extends OrmLiteBaseActivity<DBHelper> {

    private static final long SPLASH_SCREEN_DELAY = 2000;

    @InjectView(R.id.activity_splash_logan)
    protected ImageView imageViewSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_0);
        ButterKnife.inject(this);
        DBHelper.iniciarBD(getApplicationContext());
        presentarSplash();
    }

    private void startNextActivity(){
        Intent intent = new Intent(this,PostSplashActivity.class);
        startActivity(intent);
    }

    private void presentarSplash(){
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startNextActivity();
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

}
