package com.jay.gankmvp.ui.gank;

import com.jay.gankmvp.data.DailyGankData;
import com.jay.gankmvp.data.remote.ApiService;
import com.jay.gankmvp.util.RepositoryUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by jay on 17/1/5.
 */

public class GankPresenter implements GankContract.Presenter {

  private static final String TAG = GankPresenter.class.getSimpleName();

  private final ApiService mApiService;

  private final GankContract.View mView;

  private final CompositeDisposable mCompositeDisposable;

  private List<Object> mGankList;

  @Inject public GankPresenter(ApiService apiService, GankContract.View mainView) {
    mApiService = apiService;
    mView = mainView;
    mCompositeDisposable = new CompositeDisposable();
    mGankList = new ArrayList<>();
  }

  @Override public void loadDailyGankData(int year, int month, int day) {
    loadData(year, month, day);
  }

  @Override public void subscribe() {

  }

  @Override public void unsubscribe() {
    mCompositeDisposable.clear();
  }

  private void loadData(int year, int month, int day) {
    mCompositeDisposable.add(mApiService.listGank(year, month, day)
        .map(new RepositoryUtils.HttpResultFunc<DailyGankData>())
        .map(new Function<DailyGankData, List<Object>>() {
          @Override public List<Object> apply(DailyGankData dailyGankData) throws Exception {
            return addAllResults(dailyGankData.results);
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Object>>() {
          @Override public void accept(List<Object> ganks) throws Exception {
            mView.setLoadingIndicator(false);
            mView.showGank(ganks);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            mView.setLoadingIndicator(false);
            mView.showLoadingGankError(throwable);
          }
        }));
  }

  private List<Object> addAllResults(DailyGankData.Results results) {

    if (results.androidList != null) {
      mGankList.add("Android");
      mGankList.addAll(results.androidList);
    }
    if (results.iOSList != null) {
      mGankList.add("iOS");
      mGankList.addAll(results.iOSList);
    }
    if (results.webList != null) {
      mGankList.add("前端");
      mGankList.addAll(results.webList);
    }
    if (results.recommendList != null) {
      mGankList.add("瞎推荐");
      mGankList.addAll(results.recommendList);
    }
    if (results.extraList != null) {
      mGankList.add("拓展资源");
      mGankList.addAll(results.extraList);
    }
    if (results.appList != null) {
      mGankList.add("App");
      mGankList.addAll(results.appList);
    }
    return mGankList;
  }
}
