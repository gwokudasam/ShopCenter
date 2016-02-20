package com.exequiel.shopcenter.app.presentacion.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.exequiel.shopcenter.app.presentacion.fragments.WizardInicialFragment;

//Para representar los fragmentos como paginas que se mostraran en el ViewPager
public class WizardInicialAdapter extends FragmentPagerAdapter {

    private final int count = 3;

    public WizardInicialAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return WizardInicialFragment.newInstance(0);
            case 1:
                return WizardInicialFragment.newInstance(1);
            case 2:
                return WizardInicialFragment.newInstance(2);
            case 3:
                return WizardInicialFragment.newInstance(3);
            default:
                return WizardInicialFragment.newInstance(0);
        }
    }

    @Override
    public int getCount() {
        return count;
    }

}
