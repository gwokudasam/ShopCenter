package com.exequiel.shopcenter.app;

import android.support.multidex.MultiDexApplication;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by exequiel on 09/02/2016.
 */
public class ShopCenterApplication extends MultiDexApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

    }

}
