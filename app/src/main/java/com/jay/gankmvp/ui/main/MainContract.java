package com.jay.gankmvp.ui.main;

import com.jay.gankmvp.data.entity.Meizhi;
import com.jay.gankmvp.ui.base.BasePresenter;
import com.jay.gankmvp.ui.base.BaseView;

import java.util.List;

/**
 * Created by jay on 16/12/29.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMeizi(List<Meizhi> meizis);

        void showLoadingMeiziError();

        void showNoMeizis();
    }

    interface Presenter extends BasePresenter {

        void loadMeizis(boolean forceUpdate);

        void completeMeizi();
    }

}
