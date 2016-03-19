package com.exequiel.shopcenter.app.presentacion.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.data.UtilPreferences;
import com.exequiel.shopcenter.app.presentacion.adapters.ItemObject;
import com.exequiel.shopcenter.app.presentacion.adapters.NavigationAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.exequiel.shopcenter.app.presentacion.fragments.HomeFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.ProfileFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.handle.HandleFragmentsActivityGral;
import com.exequiel.shopcenter.componentes.util.UtilImagen;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

public class GralMenuActivity extends BaseActivity {

    @InjectView(R.id.drawer_layout)
    protected DrawerLayout NavDrawerLayout;

    @InjectView(R.id.drawer_options)
    protected ListView NavList;

    private Toolbar mToolbar;


    private ArrayList<ItemObject> NavItms;
    private NavigationAdapter NavAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_menu);
        HandleFragmentsActivityGral.changeFragment(HomeFragment.class, getSupportFragmentManager());
        initMenuHeader();
        initNavigationOptions();
        mToolbar        = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("titulo");
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void initMenuHeader() {
        View header = getLayoutInflater().inflate(R.layout.header, null);
        CircleImageView imgAvatar = (CircleImageView)header.findViewById(R.id.circle_image_view);
        TextView name = (TextView) header.findViewById(R.id.drawer_img_user_name);
        name.setText("RENATE SCHWARZLER");

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };

        name.setOnClickListener(listener);
        header.findViewById(R.id.circle_image_view).setOnClickListener(listener);
        header.findViewById(R.id.drawer_img_user_welcome).setOnClickListener(listener);
        NavList.addHeaderView(header);
    }

    private void initNavigationOptions() {
        String[] titulos = getResources().getStringArray(R.array.drawer_nav_options);
        NavItms = new ArrayList<>();

        NavItms.add(new ItemObject(titulos[0], R.drawable.ic_done_white_24px));
        NavItms.add(new ItemObject(titulos[1], R.drawable.ic_done_white_24px));
        NavItms.add(new ItemObject(titulos[2], R.drawable.ic_done_white_24px));
        NavItms.add(new ItemObject(titulos[3], R.drawable.ic_done_white_24px));
        NavItms.add(new ItemObject(titulos[4], R.drawable.ic_done_white_24px));
        NavItms.add(new ItemObject(titulos[5], R.drawable.ic_done_white_24px));
        NavItms.add(new ItemObject(titulos[6], R.drawable.ic_done_white_24px));
        NavItms.add(new ItemObject(titulos[7], R.drawable.ic_done_white_24px));


        //Declaramos y seteamos nuestro adaptador al cual le pasamos el array con los titulos
        NavAdapter = new NavigationAdapter(this, NavItms);
        NavList.setAdapter(NavAdapter);

        //Establecemos la accion al clickear sobre cualquier item del menu.
        //De la misma forma que hariamos en una app comun con un listview.
        NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
            }
        });
    }

    public void toggleDrawer() {
        if(!NavDrawerLayout.isDrawerOpen(Gravity.LEFT))
            NavDrawerLayout.openDrawer(Gravity.LEFT);
        else
            NavDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    public void closeDrawer() {
        if(NavDrawerLayout.isDrawerOpen(Gravity.LEFT))
            NavDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void iniciarPresentacion() {

    }
}
