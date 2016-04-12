package com.exequiel.shopcenter.app;

import android.support.multidex.MultiDexApplication;
import com.crashlytics.android.Crashlytics;
import com.exequiel.shopcenter.R;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by exequiel on 09/02/2016.
 */
public class ShopCenterApplication extends MultiDexApplication{

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/KiplingRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        Fabric.with(this, new Crashlytics());

    }

}
