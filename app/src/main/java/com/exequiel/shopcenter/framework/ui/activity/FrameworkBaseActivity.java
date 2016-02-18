package com.exequiel.shopcenter.framework.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


/**
 * Created by exequiel on 02/06/2015.
 */
public abstract class FrameworkBaseActivity extends AppCompatActivity {



    /**
     * método para llamar a crear lo basico a cualquier activity,
     * setea el ContentView y el ButterKnife
     *
     * @param savedInstanceState
     * @param resourceContent
     * El id del recourso que se seteará como un contentView en la activity
     */
    public void onCreate(Bundle savedInstanceState,int resourceContent) {
        super.onCreate(savedInstanceState);
        setContentView(resourceContent);
        ButterKnife.inject(this);
    }

}
