package com.exequiel.shopcenter.app.presentacion.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.fragments.DetalleProductoFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.handle.HandleFragmentsActivityGral;

/**
 * Created by exequiel on 19/03/2016.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private Button btnProbarApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_login);
        btnProbarApp = (Button) findViewById(R.id.btn_pruebe_app);
        btnProbarApp.setOnClickListener(this);
    }


    @Override
    public void iniciarPresentacion() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,DetalleComercioActivity.class);
        startActivity(intent);
    }
}
