package com.exequiel.shopcenter.app.presentacion.activitys;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.AvisosComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComentarioComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.Holders.ItemCuponHolder;
import com.exequiel.shopcenter.app.presentacion.adapters.ProductosComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.exequiel.shopcenter.app.presentacion.fragments.DetalleProductoFragment;
import com.exequiel.shopcenter.app.presentacion.fragments.handle.HandleFragmentsActivityGral;
import com.github.clans.fab.FloatingActionMenu;
import com.sa90.materialarcmenu.ArcMenu;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetalleComercioActivity extends AppCompatActivity
    implements AppBarLayout.OnOffsetChangedListener, View.OnTouchListener, View.OnClickListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private NestedScrollView nestedScrollView;
    private CircleImageView circleImageView;

    @InjectView(R.id.menu1)
    FloatingActionMenu menu;
    //ViewPager
    CirclePageIndicator titlePageIndicator;
    ViewPager viewPager;
    WizardInicialAdapter wizardInicialAdapter;

    Button btnHorarios;

    @InjectView(R.id.recyclerview2)
    RecyclerView recyclerView1;

    @InjectView(R.id.recyclerview3)
    RecyclerView recyclerView2;

    @InjectView(R.id.recyclerview4)
    RecyclerView recyclerView3;

    @InjectView(R.id.recyclerview5)
    RecyclerView recyclerView4;

    private void setRecyclersViews(){
        ProductosComercioAdapter recyclerViewAdapter1 = new ProductosComercioAdapter(new ItemCuponHolder.IMyViewHolderClicks() {
            @Override
            public void onItemClick(View caller) {
                Intent intent = new Intent(DetalleComercioActivity.this,DetalleProductoActivity.class);
                DetalleComercioActivity.this.startActivity(intent);
            }
        });
        final CustomLinearLayoutManager layoutManager1 = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(recyclerViewAdapter1);
        recyclerView1.setNestedScrollingEnabled(false);

        ProductosComercioAdapter recyclerViewAdapter2 = new ProductosComercioAdapter(new ItemCuponHolder.IMyViewHolderClicks() {
            @Override
            public void onItemClick(View caller) {
                Intent intent = new Intent(DetalleComercioActivity.this,DetalleProductoActivity.class);
                DetalleComercioActivity.this.startActivity(intent);
            }
        });
        final CustomLinearLayoutManager layoutManager2 = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(recyclerViewAdapter2);
        recyclerView2.setNestedScrollingEnabled(false);

        AvisosComercioAdapter recyclerViewAdapter4 = new AvisosComercioAdapter();
        final CustomLinearLayoutManager layoutManager4 = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView4.setLayoutManager(layoutManager4);
        recyclerView4.setAdapter(recyclerViewAdapter4);
        recyclerView4.setNestedScrollingEnabled(false);

        DetalleComentarioComercioAdapter recyclerViewAdapter3 = new DetalleComentarioComercioAdapter();
        final CustomLinearLayoutManager layoutManager3 = new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(recyclerViewAdapter3);
        recyclerView3.setNestedScrollingEnabled(false);
    }

    private void setToolbar(){
        mToolbar.setTitle("");
        mAppBarLayout.addOnOffsetChangedListener(this);
        setSupportActionBar(mToolbar);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        mAppBarLayout.setOnTouchListener(this);
        nestedScrollView.setOnTouchListener(this);
        mToolbar.setOnTouchListener(this);
        circleImageView.setOnTouchListener(this);
    }

    private void setBanner() {
        //ViewPager
        titlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.pager);
        wizardInicialAdapter = new WizardInicialAdapter(getSupportFragmentManager());
        viewPager.setAdapter(wizardInicialAdapter);
        titlePageIndicator.setViewPager(viewPager);
        //ViewPager
        btnHorarios = (Button) findViewById(R.id.horario_btn);
        btnHorarios.setOnClickListener(this);
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle          = (TextView) findViewById(R.id.main_textview_title);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.main_appbar);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView111);
        circleImageView = (CircleImageView) findViewById(R.id.circle_image_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_comercio);
        ButterKnife.inject(this);
        setRecyclersViews();
        bindActivity();
        setToolbar();
        setBanner();
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (menu.isOpened())
            menu.close(true);
        return false;
    }

    private void showCustomDialog(int idResourseLayout){
        Dialog customDialog=new Dialog(this, R.style.Theme_Dialog_Translucent);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(idResourseLayout);
        customDialog.show();
    }

    @OnClick({R.id.fab1_contactar,R.id.fab2_como_llegar,R.id.fab3_redes_sociales,
    R.id.fab4_seguir,R.id.fab5_compartir,R.id.fab6_puntuar})
    public void clickMenu(View v){
        switch (v.getId()){
            case R.id.fab1_contactar :{
                break;
            }
            case R.id.fab2_como_llegar :{
                break;
            }
            case R.id.fab3_redes_sociales :{
                break;
            }
            case R.id.fab4_seguir :{
                break;
            }
            case R.id.fab5_compartir :{
                break;
            }
            case R.id.fab6_puntuar :{
                showCustomDialog(R.layout.dialogo_puntuar);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        showCustomDialog(R.layout.dialog_horarios);
    }
}
