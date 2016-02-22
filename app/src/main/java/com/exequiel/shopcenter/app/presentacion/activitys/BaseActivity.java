package com.exequiel.shopcenter.app.presentacion.activitys;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.exequiel.shopcenter.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by exequiel on 02/06/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * método para llamar a crear lo basico a cualquier activity,
     * setea el ContentView, el ButterKnife y llama al método
     * iniciarPresentación
     *
     * @param savedInstanceState
     * @param resourceContent    El id del recourso que se seteará como un contentView en la activity
     */
    public void onCreate(Bundle savedInstanceState, int resourceContent) {
        super.onCreate(savedInstanceState);
        setContentView(resourceContent);
        ButterKnife.inject(this);
        iniciarPresentacion();
    }

    public abstract void iniciarPresentacion();

}
