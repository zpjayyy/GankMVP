package com.jay.gankmvp.net;

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
