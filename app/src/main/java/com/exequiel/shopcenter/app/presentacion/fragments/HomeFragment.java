package com.exequiel.shopcenter.app.presentacion.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.ItemComercioNuevoAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.exequiel.shopcenter.app.presentacion.fragments.handle.HandleFragmentsActivityGral;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by exequiel on 09/07/2015.
 */
public class HomeFragment extends FrameworkBaseFragment {


    private static final String TAG_FRAGMENT = "HOME_FRAGMENT";

    //ViewPager
    //@InjectView(R.id.indicator)
    protected CirclePageIndicator titlePageIndicator;

    //@InjectView(R.id.pager)
    protected ViewPager viewPager;

    RecyclerView recyclerView3;

    private WizardInicialAdapter wizardInicialAdapter;

    int currentItem;

    private PullToZoomScrollViewEx scrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = onCreateView(inflater, container, savedInstanceState, R.layout.fragment_pull_to_zoom_scroll_home);
        loadZoomView(view);
        loadPullToZoomView(view);
        return view;
    }

    private void loadPullToZoomView(View view){
        scrollView = (PullToZoomScrollViewEx) view.findViewById(R.id.scroll_view);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

    private void loadZoomView(View view) {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) view.findViewById(R.id.scroll_view);
        View headView = LayoutInflater.from(view.getContext()).inflate(R.layout.home_head_view, null, false);
        View zoomView = LayoutInflater.from(view.getContext()).inflate(R.layout.home_zoom_view, null, false);
        setViewPager(zoomView);
        View contentView = LayoutInflater.from(view.getContext()).inflate(R.layout.home_parte_baja, null, false);
        setContentViewZoom(contentView);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setZoomEnabled(true);
        scrollView.setParallax(true);
        scrollView.setScrollContentView(contentView);
    }

    private void setContentViewZoom(View contentView) {
        ItemComercioNuevoAdapter recyclerViewAdapter3 = new ItemComercioNuevoAdapter();
        recyclerView3 = (RecyclerView) contentView.findViewById(R.id.recyclerviewlastcomer);
        final CustomLinearLayoutManager layoutManager3 = new CustomLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager = new LinearLayoutManager(this);
        //final MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, getScreenHeight(this));
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(recyclerViewAdapter3);
        recyclerView3.setNestedScrollingEnabled(false);
    }

    private void setViewPager(View view){
        titlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        wizardInicialAdapter = new WizardInicialAdapter(getFragmentManager());
        viewPager.setAdapter(wizardInicialAdapter);
        titlePageIndicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        final Handler handler = new Handler();
        currentItem = viewPager.getCurrentItem();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentItem == 3) {
                    currentItem = 0;
                }
                viewPager.setCurrentItem(currentItem++, true);
            }
        };
        final Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }

        }, 1000, 4500);

        //ViewPager
    }

    //@OnClick({R.id.home_card_buscar_productos,R.id.home_card_cupones,R.id.home_card_tiendas_cercanas,R.id.home_card_ver_comercios})
    public void click(View view){
        switch (view.getId()){
            case R.id.home_card_buscar_productos:{
                HandleFragmentsActivityGral.changeFragment(AgendaFragment.class,getFragmentManager());
                break;
            }
            case R.id.home_card_cupones:{
                HandleFragmentsActivityGral.changeFragment(CuponesFragment.class,getFragmentManager());
                break;
            }
            case R.id.home_card_tiendas_cercanas:{
                break;
            }
            case R.id.home_card_ver_comercios:{
                HandleFragmentsActivityGral.changeFragment(BuscarComercios.class,getFragmentManager());
                break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        deseleccionarItems();
    }

    private void deseleccionarItems(){
        CircleImageView imgComercio;
        int childCount = recyclerView3.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View v = recyclerView3.getChildAt(i);
            imgComercio = (CircleImageView) v.findViewById(R.id.last_comercio_circle_img);
            imgComercio.setBorderColorResource(android.R.color.white);
        }
    }

    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }

}
