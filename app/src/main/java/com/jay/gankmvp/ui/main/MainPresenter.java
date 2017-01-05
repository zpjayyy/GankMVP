package com.jay.gankmvp.ui.main;

import com.jay.gankmvp.data.GankData;
import com.jay.gankmvp.data.entity.Gank;
import com.jay.gankmvp.data.remote.ApiService;
import com.jay.gankmvp.util.RepositoryUtils;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jay on 16/12/29.
 */

public class MainPresenter implements MainContract.Presenter {

  private final ApiService mApiService;

  private final CompositeDisposable mCompositeDisposable;

  private final MainContract.View mMainView;

  private int mPage = 1;

  @Inject MainPresenter(ApiService apiService, MainContract.View mainView) {
    mApiService = apiService;
    mMainView = mainView;
    mCompositeDisposable = new CompositeDisposable();
  }

  @Override public void subscribe() {
    loadMeizis(false);
  }

  @Override public void unsubscribe() {
    mCompositeDisposable.clear();
  }

  @Override public void loadMeizis(boolean forceUpdate) {
    mMainView.setLoadingIndicator(true);
    if (forceUpdate) {
      mPage = 1;
    }
    loadMeizis(mPage);
  }

  private void loadMeizis(int page) {
    mCompositeDisposable.add(Flowable.zip(mApiService.listMeizi(page), mApiService.listVideo(page),
        new BiFunction<GankData, GankData, GankData>() {
          @Override public GankData apply(GankData meizhiData, GankData videoData)
              throws Exception {
            for (int i = 0; i < meizhiData.results.size(); i++) {
              meizhiData.results.get(i).desc = videoData.results.get(i).desc;
            }
            return meizhiData;
          }
        })
        .map(new RepositoryUtils.HttpResultFunc<GankData>())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<GankData>() {
          @Override public void accept(GankData gankData) throws Exception {
            mPage++;
            mMainView.setLoadingIndicator(false);
            mMainView.showMeizi(gankData.results);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            mMainView.setLoadingIndicator(false);
            mMainView.showLoadingMeiziError(throwable);
          }
        }));
  }
}
