package com.jay.gankmvp;

import android.content.Context;

import dagger.Component;

/**
 * Created by jay on 16/12/21.
 */

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context getApplicationContext();

}
