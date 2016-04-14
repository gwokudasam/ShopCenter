package com.exequiel.shopcenter.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import com.crashlytics.android.Crashlytics;
import com.exequiel.shopcenter.R;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by exequiel on 09/02/2016.
 */
public class ShopCenterApplication extends MultiDexApplication{

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig calligraphyConfig = new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CenturyGothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(calligraphyConfig);

        Fabric.with(this, new Crashlytics());

    }

}
