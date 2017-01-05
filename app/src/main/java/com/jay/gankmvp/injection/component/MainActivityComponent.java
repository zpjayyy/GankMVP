package com.jay.gankmvp.injection.component;

import com.jay.gankmvp.injection.ActivityScoped;
import com.jay.gankmvp.injection.module.MainPresenterModule;
import com.jay.gankmvp.ui.main.MainPresenter;

import dagger.Component;

/**
 * Created by jay on 16/12/29.
 */

@ActivityScoped @Component(dependencies = ApiComponent.class, modules = MainPresenterModule.class)
public interface MainActivityComponent {

  MainPresenter getMainPresenter();
}
