package com.jay.gankmvp.injection.component;

import com.jay.gankmvp.injection.ActivityScoped;
import com.jay.gankmvp.injection.module.GankActivityModule;
import com.jay.gankmvp.ui.gank.GankPresenter;
import dagger.Component;

/**
 * Created by jay on 17/1/5.
 */

@ActivityScoped
@Component(dependencies = { ApiComponent.class }, modules = { GankActivityModule.class })
public interface GankActivityComponent {

  GankPresenter getGankPresenter();
}
