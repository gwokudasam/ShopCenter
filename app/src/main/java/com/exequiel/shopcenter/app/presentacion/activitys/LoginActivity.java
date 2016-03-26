package com.exequiel.shopcenter.app.presentacion.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.exequiel.shopcenter.R;

import butterknife.InjectView;
import butterknife.OnClick;

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

    @OnClick({R.id.tx_registrarse, R.id.tx_perdi_pass})
    public void click(View v){
        switch (v.getId()){
            case R.id.tx_registrarse:{
                Intent intent = new Intent(this,RegistrarseActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tx_perdi_pass:{
                Intent intent = new Intent(this,ForgotPassActivity.class);
                startActivity(intent);
                break;
            }
        }

    }

    @Override
    public void iniciarPresentacion() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,GeneralActivity.class);
        startActivity(intent);
    }
}
