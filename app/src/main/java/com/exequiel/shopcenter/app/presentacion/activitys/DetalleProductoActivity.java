package com.exequiel.shopcenter.app.presentacion.activitys;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComentarioComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.OnClick;

/**
 * Created by exequiel on 25/03/2016.
 */
public class DetalleProductoActivity extends BaseActivity {

    private PullToZoomScrollViewEx scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_scroll_view);
        loadViewForCode();
        scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

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

    private void setContentViewZoom(View view){
        TextView txAntes = (TextView) view.findViewById(R.id.textView18);
        //ViewPager
        DetalleComentarioComercioAdapter recyclerViewAdapter3 = new DetalleComentarioComercioAdapter();
        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerview4);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear_desc_product);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDesc(v);
            }
        });
        final CustomLinearLayoutManager layoutManager3 = new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(recyclerViewAdapter3);
        recyclerView3.setNestedScrollingEnabled(false);
        tacharTexto(txAntes," $ 670 ");
    }

    private void loadViewForCode() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        View headView = LayoutInflater.from(this).inflate(R.layout.detalle_producto_head_view, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.detalle_producto_zoom_view, null, false);
        setViewPager(zoomView);
        View contentView = LayoutInflater.from(this).inflate(R.layout.detalle_producto_parte_baja, null, false);
        setContentViewZoom(contentView);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setZoomEnabled(true);
        scrollView.setParallax(true);
        scrollView.setScrollContentView(contentView);
    }

    private void tacharTexto(TextView txt,String texto){
        // Use a SpannableStringBuilder so that both the text and the spans are mutable
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        // Create a span that will strikethrough the text
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        // Add the secondWord and apply the strikethrough span to only the second word
        ssb.append(texto);
        ssb.setSpan(
                strikethroughSpan,
                ssb.length() - texto.length(),
                ssb.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the TextView text and denote that it is Editable
        // since it's a SpannableStringBuilder
        txt.setText(ssb, TextView.BufferType.EDITABLE);
    }

    public void clickDesc(View v){
        Dialog customDialog=new Dialog(this, R.style.Theme_Dialog_Translucent);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.dialog_descripcion);
        customDialog.show();
    }


    @Override
    public void iniciarPresentacion() {

    }
}
