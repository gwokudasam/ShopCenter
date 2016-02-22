package com.exequiel.shopcenter.app.presentacion.fragments.handle;

import android.os.Bundle;

/**
 * Created by exe on 12/11/2015.
 */
public class CargaUtilFragment {

    private TYPE_FRAGMENT_GENERAL typeFragment;
    private Bundle cargaUtil;

    public CargaUtilFragment(TYPE_FRAGMENT_GENERAL typeFragment, Bundle bundle) {
        this.typeFragment =typeFragment;
        this.cargaUtil = bundle;
    }

    public TYPE_FRAGMENT_GENERAL getTypeFragment() {
        return typeFragment;
    }

    public void setTypeFragment(TYPE_FRAGMENT_GENERAL typeFragment) {
        this.typeFragment = typeFragment;
    }

    public Bundle getCargaUtil() {
        return cargaUtil;
    }

    public void setCargaUtil(Bundle cargaUtil) {
        this.cargaUtil = cargaUtil;
    }
}
