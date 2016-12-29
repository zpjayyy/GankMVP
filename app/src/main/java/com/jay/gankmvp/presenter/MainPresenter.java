package com.jay.gankmvp.presenter;

import com.jay.gankmvp.data.MeizhiData;
import com.jay.gankmvp.net.ApiService;
import com.jay.gankmvp.presenter.contract.MainContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jay on 16/12/29.
 */

public class MainPresenter implements MainContract.Presenter {

    private final ApiService mApiService;

    private final CompositeDisposable mCompositeDisposable;

    private final MainContract.View mMainView;

    @Inject
    MainPresenter(ApiService apiService, MainContract.View mainView) {
        mApiService = apiService;
        mMainView = mainView;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        loadMeizis(false);
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void loadMeizis(boolean forceUpdate) {
        loadMeizis(forceUpdate, false);
    }

    @Override
    public void completeMeizi() {

    }

    private void loadMeizis(boolean forceUpdate, final boolean showLoadingUI) {
        mCompositeDisposable.add(mApiService.listMeizi("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MeizhiData>() {
                    @Override
                    public void accept(MeizhiData meizhiData) throws Exception {
                        mMainView.setLoadingIndicator(false);
                        mMainView.showMeizi(meizhiData.results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mMainView.setLoadingIndicator(false);
                        mMainView.showLoadingMeiziError();
                    }
                }));

    }
}
