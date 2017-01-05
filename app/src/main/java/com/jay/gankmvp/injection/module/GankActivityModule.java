package com.jay.gankmvp.injection.module;

import com.jay.gankmvp.ui.gank.GankContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jay on 17/1/5.
 */

@Module public class GankActivityModule {

  private final GankContract.View mView;

  public GankActivityModule(GankContract.View view) {
    this.mView = view;
  }

  @Provides public GankContract.View providerGankContractView() {
    return mView;
  }
}
