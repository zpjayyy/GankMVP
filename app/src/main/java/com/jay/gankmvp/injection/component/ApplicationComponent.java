package com.jay.gankmvp.injection.component;

import android.content.Context;

import com.jay.gankmvp.injection.module.ApplicationModule;

import dagger.Component;

/**
 * Created by jay on 16/12/21.
 */

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context getApplicationContext();

}
