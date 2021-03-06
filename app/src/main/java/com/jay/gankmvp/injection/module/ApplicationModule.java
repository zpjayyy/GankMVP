package com.jay.gankmvp.injection.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jay on 16/12/21.
 */

@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context provideApplicationContext() {
        return mContext;
    }

}
