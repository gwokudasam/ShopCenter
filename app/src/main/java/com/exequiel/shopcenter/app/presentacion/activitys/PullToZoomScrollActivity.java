package com.exequiel.shopcenter.app.presentacion.activitys;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by exequiel on 31/03/2016.
 */
public class PullToZoomScrollActivity extends ActionBarActivity {

    private PullToZoomScrollViewEx scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_scroll_view);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadViewForCode();
        scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        /*
        scrollView.getPullRootView().findViewById(R.id.tv_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zhuwenwu", "onClick -->");
            }
        });

        scrollView.getPullRootView().findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zhuwenwu", "onClick -->");
            }
        });

        scrollView.getPullRootView().findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zhuwenwu", "onClick -->");
            }
        });
        */
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scroll_view, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
//        else if (id == R.id.action_settings) {
//            loadViewForCode();
//            return true;
//        }
        else if (id == R.id.action_normal) {
            scrollView.setParallax(false);
            return true;
        } else if (id == R.id.action_parallax) {
            scrollView.setParallax(true);
            return true;
        } else if (id == R.id.action_show_head) {
//            scrollView.showHeaderView();
            scrollView.setHideHeader(false);
            return true;
        } else if (id == R.id.action_hide_head) {
//            scrollView.hideHeaderVie?w();
            scrollView.setHideHeader(true);
            return true;
        } else if (id == R.id.action_disable_zoom) {
//            scrollView.setEnableZoom(false);
            scrollView.setZoomEnabled(false);
            return true;
        } else if (id == R.id.action_enable_zoom) {
//            scrollView.setEnableZoom(true);
            scrollView.setZoomEnabled(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */

    private void setViewPager(View view){
        CirclePageIndicator titlePageIndicator;
        ViewPager viewPager;
        WizardInicialAdapter wizardInicialAdapter;
        //ViewPager
        titlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        wizardInicialAdapter = new WizardInicialAdapter(getSupportFragmentManager());
        viewPager.setAdapter(wizardInicialAdapter);
        titlePageIndicator.setViewPager(viewPager);
        //ViewPager
    }

    private void loadViewForCode() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        View headView = LayoutInflater.from(this).inflate(R.layout.detalle_producto_head_view, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.detalle_producto_zoom_view, null, false);
        setViewPager(zoomView);
        View contentView = LayoutInflater.from(this).inflate(R.layout.detalle_producto_parte_baja, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setZoomEnabled(true);
        scrollView.setParallax(true);
        scrollView.setScrollContentView(contentView);
    }
}

