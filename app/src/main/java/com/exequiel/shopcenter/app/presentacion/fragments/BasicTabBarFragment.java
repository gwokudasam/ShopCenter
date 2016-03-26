package com.exequiel.shopcenter.app.presentacion.fragments;

import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

/**
 * Created by exequiel on 10/09/2015.
 */
public abstract class BasicTabBarFragment extends FrameworkBaseFragment implements TabHost.OnTabChangeListener  {

    private IBasicTabBarCustomFragment referencia;
    private TabHost tabs;

    protected void setTabs(IBasicTabBarCustomFragment referencia, View view){
        this.referencia = referencia;
        int count = this.referencia.getCountTabs();
        tabs = (TabHost) view.findViewById(android.R.id.tabhost);
        tabs.setup();
        int[] listResourcesTab = referencia.getResourcesTab();
        TabHost.TabSpec spec= null;
        for (int i=0;i<count;i++){
            spec=tabs.newTabSpec("mitab"+String.valueOf(i));
            spec.setContent(listResourcesTab[i]);
            if (this.referencia.getTitles()!=null && this.referencia.getTitles()[i]!=null){
                spec.setIndicator(this.referencia.getTitles()[i]);
            }else{
                spec.setIndicator("DEFAULT");
            }
            tabs.addTab(spec);
        }
        tabs.setOnTabChangedListener(this);
        tabs.setCurrentTab(0);
        initializeTabs(tabs.getTabWidget());
        onTabChanged("");
    }

    private void initializeTabs(TabWidget tabWidget) {
        // Change background
        for(int i=0; i < tabWidget.getChildCount(); i++){
            View view = tabWidget.getChildAt(i);
            TextView tv =  (TextView) view.findViewById ( android . R . id . title );
            if ( tv ==  null )  {
                continue ;
            }else{
                CalligraphyUtils.applyFontToTextView(getActivity(), tv, "fonts/CenturyGothicBold.ttf");
            }
            tabWidget.getChildAt(i).setBackgroundResource(R.drawable.tab_indicator_ab_verde);
        }
    }

    @Override
    public void onTabChanged(String s) {
        int i = tabs.getCurrentTab();
        updateViews(tabs.getTabWidget());
        this.referencia.onTabChanged(i);
    }

    private void updateViews(TabWidget tabWidget) {
        int iCurrent = tabs.getCurrentTab();
        for(int i=0; i < tabWidget.getChildCount(); i++){
            View view = tabWidget.getChildAt(i);
            TextView tv =  (TextView) view.findViewById ( android . R . id . title );
            if ( tv ==  null )  {
                continue ;
            }else{
                if (iCurrent==i){
                    tv.setTextColor(getResources().getColor(R.color.primary));
                }else{
                    tv.setTextColor(getResources().getColor(R.color.material_blue_grey_80));
                }
            }
        }
    }


    public interface IBasicTabBarCustomFragment{
        int getCountTabs();
        String[] getTitles();
        /**
         * No es el click, solo el cambio de pestaña
         */
        void onTabChanged(int pestania);
        int[] getResourcesTab();
    }

}
