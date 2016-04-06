package com.exequiel.shopcenter.app.presentacion.activitys;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.exequiel.shopcenter.R;

/**
 * Created by exequiel on 06/04/2016.
 */
public class DetalleSorteoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_scroll_view);
        loadZoomView();
        loadPullToZoomView();
    }

    private void loadZoomView() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        View headView = LayoutInflater.from(this).inflate(R.layout.detalle_producto_head_view, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.sorteo_zoom_view, null, false);
        View contentView = LayoutInflater.from(this).inflate(R.layout.sorteo_cuerpo, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setZoomEnabled(true);
        scrollView.setParallax(true);
        scrollView.setScrollContentView(contentView);
    }

    private void loadPullToZoomView(){
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

    @Override
    public void iniciarPresentacion() {

    }
}
