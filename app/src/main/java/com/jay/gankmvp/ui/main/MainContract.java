package com.jay.gankmvp.ui.main;

import com.jay.gankmvp.data.entity.Gank;
import com.jay.gankmvp.ui.base.BasePresenter;
import com.jay.gankmvp.ui.base.BaseView;

import java.util.List;

/**
 * Created by jay on 16/12/29.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMeizi(List<Gank> meizis);

        void showLoadingMeiziError(Throwable throwable);

    }

    interface Presenter extends BasePresenter {

        void loadMeizis(boolean forceUpdate);

    }

}
