package com.jay.gankmvp.ui;

import com.jay.gankmvp.net.ApiComponent;
import com.jay.gankmvp.presenter.MainPresenter;
import com.jay.gankmvp.util.ActivityScoped;

import dagger.Component;

/**
 * Created by jay on 16/12/29.
 */

@ActivityScoped
@Component(dependencies = ApiComponent.class, modules = MainPresenterModule.class)
public interface MainActivityComponent {

    MainPresenter getMainPresenter();

}
