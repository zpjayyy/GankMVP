package com.jay.gankmvp.injection.component;

import com.jay.gankmvp.data.remote.ApiService;
import com.jay.gankmvp.injection.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by jay on 16/12/21.
 */

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {

    ApiService getApiService();

    OkHttpClient getOkHttpClient();

}
