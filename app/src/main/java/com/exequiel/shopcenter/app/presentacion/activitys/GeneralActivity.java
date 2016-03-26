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
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.data.UtilPreferences;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.ItemMenuLateralHolder;
import com.exequiel.shopcenter.app.presentacion.adapters.ItemObject;
import com.exequiel.shopcenter.app.presentacion.adapters.NavigationAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.exequiel.shopcenter.app.presentacion.fragments.AgendaFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.ContactFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.CuponesFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.DetalleProductoFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.HomeFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.ListProductsFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.ProfileFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.handle.HandleFragmentsActivityGral;
import com.exequiel.shopcenter.componentes.util.UtilImagen;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

public class GeneralActivity extends BaseActivity {

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
        initMenuHeader();
        initNavigationOptions();
        mToolbar        = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("titulo");
        setSupportActionBar(mToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        HandleFragmentsActivityGral.changeFragment(HomeFragment.class, getSupportFragmentManager());
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
        NavDrawerLayout.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                Log.i("Menu lateral", "onCreateContextMenu");

            }
        });
        NavAdapter = new NavigationAdapter(this, NavItms);
        NavList.setAdapter(NavAdapter);

        NavList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Menu lateral", "long lstener");
                return false;
            }
        });

        NavList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Menu lateral", "selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("Menu lateral", "nothing selected");

            }
        });
        NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                navigationOptionSelected(position);
                seleccionarItem(position);
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        toggleDrawer();
        return super.onOptionsItemSelected(item);
    }

    private void seleccionarItem(int position){
        int childCount = NavList.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View v = NavList.getChildAt(i);
            if (i!=0&&i!=position){
                if (position!=0)
                    ((ItemMenuLateralHolder) v.getTag()).setItemUnselect();
            }else if(i!=0){
                ((ItemMenuLateralHolder) v.getTag()).setItemSelected();
            }
        }
    }

    private void navigationOptionSelected(int position) {
        switch (position) {
            case 1:
                HandleFragmentsActivityGral.changeFragment(HomeFragment.class, getSupportFragmentManager());
                break;
            case 2:
                HandleFragmentsActivityGral.changeFragment(ProfileFragment.class, getSupportFragmentManager());
                break;
            case 3:
                HandleFragmentsActivityGral.changeFragment(CuponesFragment.class, getSupportFragmentManager());
                break;
            case 4:
                HandleFragmentsActivityGral.changeFragment(AgendaFragment.class, getSupportFragmentManager());
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                HandleFragmentsActivityGral.changeFragment(ContactFragment.class, getSupportFragmentManager());
                break;
        }
        if (position!=0)
            closeDrawer();
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

    @Override
    public void onBackPressed() {
        if (NavDrawerLayout.isDrawerOpen(Gravity.LEFT))
            NavDrawerLayout.closeDrawer(Gravity.LEFT);
        else
            super.onBackPressed();
    }
}
