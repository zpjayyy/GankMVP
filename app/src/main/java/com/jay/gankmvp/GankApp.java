package com.jay.gankmvp;

import android.app.Application;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.facebook.stetho.Stetho;
import com.jay.gankmvp.injection.component.ApiComponent;
import com.jay.gankmvp.injection.component.ApplicationComponent;
import com.jay.gankmvp.injection.component.DaggerApiComponent;
import com.jay.gankmvp.injection.component.DaggerApplicationComponent;
import com.jay.gankmvp.injection.module.ApiModule;
import com.jay.gankmvp.injection.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashSet;
import java.util.Set;

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

        mApiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();

        initFresco();
    }

    private void initFresco() {
        Set<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, mApiComponent.getOkHttpClient())
                .setRequestListeners(requestListeners)
                .build();
        Fresco.initialize(this, config);
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
