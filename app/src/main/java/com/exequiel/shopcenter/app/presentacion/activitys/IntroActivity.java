package com.exequiel.shopcenter.app.presentacion.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.exequiel.shopcenter.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by exequiel on 20/02/2016.
 */
public class IntroActivity extends AppIntro2 {

    // Please DO NOT override onCreate. Use init.
    @Override
    public void init(Bundle savedInstanceState) {
/*
        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(first_fragment);
        addSlide(second_fragment);
        addSlide(third_fragment);
        addSlide(fourth_fragment);*/

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_1_title), getString(R.string.intro_1_descripcion), R.drawable.ic_logo, Color.parseColor("#FF47B612")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_2_title), getString(R.string.intro_2_descripcion), R.drawable.ic_logo, Color.parseColor("#FF47B612")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_3_title), getString(R.string.intro_3_descripcion), R.drawable.ic_logo, Color.parseColor("#FF47B612")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_4_title), getString(R.string.intro_4_descripcion), R.drawable.ic_logo, Color.parseColor("#FF47B612")));
        setFlowAnimation();
        /*
        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);*/
        //setProgressButtonEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.

        setVibrate(true);
        setVibrateIntensity(40);
    }
/*
    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
    }*/

    @Override
    public void onDonePressed() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged() {
        // Do something when the slide changes.
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

}