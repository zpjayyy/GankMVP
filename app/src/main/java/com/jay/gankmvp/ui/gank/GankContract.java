package com.jay.gankmvp.ui.gank;

import com.jay.gankmvp.ui.base.BasePresenter;
import com.jay.gankmvp.ui.base.BaseView;
import com.jay.gankmvp.ui.main.MainContract;
import java.util.List;

/**
 * Created by jay on 17/1/5.
 */

public interface GankContract {

  interface View extends BaseView<MainContract.Presenter> {

    void setLoadingIndicator(boolean active);

    void showGank(List<Object> gankList);

    void showLoadingGankError(Throwable throwable);
  }

  interface Presenter extends BasePresenter {

    void loadDailyGankData(int year, int month, int day);
  }

}
