package com.jay.gankmvp;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

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
