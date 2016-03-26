package com.exequiel.shopcenter.app.presentacion.fragments.handle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.fragments.WebViewFragment;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;


/**
 * Manejador de cambios entre fragmentos
 * en la activity del menú general.
 * Created by exequiel on 21/08/2015.
 */
public class HandleFragmentsActivityGral {

    public static FrameworkBaseFragment createInstanceFragment(Class<? extends FrameworkBaseFragment> classFragment, Bundle bundle) {
        FrameworkBaseFragment fragment = null;
        try {
            fragment = classFragment.newInstance();
            if (bundle != null)
                fragment.setArguments(bundle);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    private static void removeAllFragments(FragmentManager fm) {
        for (Fragment fragment : fm.getFragments()) {
            if (fragment instanceof FrameworkBaseFragment) {
                fm.beginTransaction().remove(fragment).commit();
            }
        }
    }

    public static void changeFragment(Class<? extends FrameworkBaseFragment> classFragment, FragmentManager fm) {
        changeFragment(classFragment, fm, null);
    }

    public static void changeFragment(Class<? extends FrameworkBaseFragment> classFragment, FragmentManager fm, Bundle bundle) {

        FrameworkBaseFragment fragment = createInstanceFragment(classFragment, bundle);
        String tag = fragment.getTagFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.drawer_container, fragment, tag);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void putWebFragment(FragmentManager fm, String url,int resource){
        Bundle bundle=null;
        if (url!=null) {
            bundle = new Bundle();
            bundle.putString(WebViewFragment.KEY_URL_LOAD, url);
        }
        WebViewFragment webFragment = new WebViewFragment();
        FragmentTransaction ft = fm.beginTransaction();
        webFragment.setArguments(bundle);
        ft.replace(resource, webFragment, webFragment.getTagFragment());
        ft.commit();
    }

}