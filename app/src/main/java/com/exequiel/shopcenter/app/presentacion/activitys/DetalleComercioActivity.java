package com.exequiel.shopcenter.app.presentacion.activitys;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComentarioComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class DetalleComercioActivity extends AppCompatActivity
    implements AppBarLayout.OnOffsetChangedListener{

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    //ViewPager
    CirclePageIndicator titlePageIndicator;
    ViewPager viewPager;
    WizardInicialAdapter wizardInicialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_comercio);

        //RecyclerViewAdapter

        DetalleComercioAdapter recyclerViewAdapter = new DetalleComercioAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        final CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager = new LinearLayoutManager(this);
        //final MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, getScreenHeight(this));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        DetalleComercioAdapter recyclerViewAdapter1 = new DetalleComercioAdapter();
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recyclerview2);
        final CustomLinearLayoutManager layoutManager1 = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager = new LinearLayoutManager(this);
        //final MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, getScreenHeight(this));
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(recyclerViewAdapter1);
        recyclerView1.setNestedScrollingEnabled(false);

        DetalleComercioAdapter recyclerViewAdapter2 = new DetalleComercioAdapter();
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recyclerview3);
        final CustomLinearLayoutManager layoutManager2 = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager = new LinearLayoutManager(this);
        //final MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, getScreenHeight(this));
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(recyclerViewAdapter2);
        recyclerView2.setNestedScrollingEnabled(false);

        DetalleComentarioComercioAdapter recyclerViewAdapter3 = new DetalleComentarioComercioAdapter();
        RecyclerView recyclerView3 = (RecyclerView) findViewById(R.id.recyclerview4);
        final CustomLinearLayoutManager layoutManager3 = new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager = new LinearLayoutManager(this);
        //final MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, getScreenHeight(this));
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(recyclerViewAdapter3);
        recyclerView3.setNestedScrollingEnabled(false);

        //RecyclerViewAdapter

        bindActivity();

        mToolbar.setTitle("");
        mAppBarLayout.addOnOffsetChangedListener(this);


        setSupportActionBar(mToolbar);

        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle          = (TextView) findViewById(R.id.main_textview_title);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.main_appbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
            if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

                if(!mIsTheTitleVisible) {
                    startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                    mIsTheTitleVisible = true;
                }

            } else {

                if (mIsTheTitleVisible) {
                    startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                    mIsTheTitleVisible = false;
                }
            }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
            ? new AlphaAnimation(0f, 1f)
            : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
