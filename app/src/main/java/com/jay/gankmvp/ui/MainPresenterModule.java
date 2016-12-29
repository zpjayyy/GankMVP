package com.jay.gankmvp.ui;

import com.jay.gankmvp.presenter.contract.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jay on 16/12/29.
 */

@Module
public class MainPresenterModule {

    private final MainContract.View mView;

    public MainPresenterModule(MainContract.View view) {
        this.mView = view;
    }

    @Provides
    MainContract.View provideMainContractView() {
        return mView;
    }
}
