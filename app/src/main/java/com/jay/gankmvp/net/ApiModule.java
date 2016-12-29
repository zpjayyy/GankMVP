package com.jay.gankmvp.net;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jay.gankmvp.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jay on 16/12/21.
 */

@Module
public class ApiModule {

    private static final String BASE_URL = "http://gank.io/api/";

    @Provides
    @Singleton
    public Gson provideGson() {
        Gson gson = new GsonBuilder()
                .create();
        return gson;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOKHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);

            builder.addInterceptor(new StethoInterceptor());
        }

        builder.retryOnConnectionFailure(true)
                .readTimeout(15, TimeUnit.SECONDS);

        return builder.build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(OkHttpClient client, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiService.class);
    }
}
