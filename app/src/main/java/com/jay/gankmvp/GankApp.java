package com.jay.gankmvp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jay.gankmvp.data.ApiComponent;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by jay on 16/12/21.
 */

public class GankApp extends Application {

    private ApplicationComponent mApplicationComponent;
    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        LeakCanary.install(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }
}
