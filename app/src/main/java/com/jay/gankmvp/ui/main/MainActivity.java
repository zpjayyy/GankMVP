package com.jay.gankmvp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.Scene;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jay.gankmvp.GankApp;
import com.jay.gankmvp.R;
import com.jay.gankmvp.config.Constant;
import com.jay.gankmvp.data.entity.Gank;
import com.jay.gankmvp.data.remote.ErrorMessageFactory;
import com.jay.gankmvp.func.OnItemTouchListener;
import com.jay.gankmvp.injection.component.DaggerMainActivityComponent;
import com.jay.gankmvp.injection.module.MainActivityModule;
import com.jay.gankmvp.provide.MeizhiViewProvider;
import com.jay.gankmvp.ui.base.ToolbarActivity;
import com.jay.gankmvp.ui.gank.GankActivity;
import java.util.List;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends ToolbarActivity implements MainContract.View {

  @BindView(R.id.recyclerview) RecyclerView mRecyclerview;
  @BindView(R.id.layout_refresh) SwipeRefreshLayout mLayoutRefresh;
  @BindView(R.id.fab) FloatingActionButton mFab;

  MainPresenter mMainPresenter;

  MultiTypeAdapter mAdapter;
  MeizhiViewProvider mMeizhiViewProvider;
  Items mItems;

  boolean mIsFirstTimeTouchBottom = true;

  @Override protected int provideContentViewId() {
    return R.layout.activity_main;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);

    mMainPresenter = DaggerMainActivityComponent.builder()
        .apiComponent(((GankApp) getApplication()).getApiComponent())
        .mainActivityModule(new MainActivityModule(this))
        .build()
        .getMainPresenter();

    mMainPresenter.loadMeizis(false);

    initView();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mMainPresenter.unsubscribe();
  }

  private void initView() {
    final StaggeredGridLayoutManager manager =
        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

    mRecyclerview.setLayoutManager(manager);

    //        int space = getResources().getDimensionPixelSize(R.dimen.meizhi_item_space);
    //        mRecyclerview.addItemDecoration(new MeizhiItemDecoration(space));
    mRecyclerview.setItemAnimator(new FadeInAnimator());

    mItems = new Items();
    mAdapter = new MultiTypeAdapter(mItems);

    mMeizhiViewProvider = new MeizhiViewProvider();

    mAdapter.register(Gank.class, mMeizhiViewProvider);

    mRecyclerview.setAdapter(mAdapter);

    mLayoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mItems.clear();
        mMainPresenter.loadMeizis(true);
      }
    });

    mMeizhiViewProvider.setOnItemTouchListener(getOnItemTouchListener());

    mRecyclerview.addOnScrollListener(getBottomListener(manager));

  }

  RecyclerView.OnScrollListener getBottomListener(final StaggeredGridLayoutManager layoutManager) {
    return new RecyclerView.OnScrollListener() {
      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        boolean isBottom = layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1]
            >= mAdapter.getItemCount() - Constant.PRELOAD_SIZE;

        if (!mLayoutRefresh.isRefreshing() && isBottom) {
          if (!mIsFirstTimeTouchBottom) {
            mMainPresenter.loadMeizis(false);
          } else {
            mIsFirstTimeTouchBottom = false;
          }
        }
      }
    };
  }

  OnItemTouchListener getOnItemTouchListener() {
    return new OnItemTouchListener() {
      @Override public void onTouch(View v, View meizhiView, Gank gank) {

        Intent intent = GankActivity.newIntent(MainActivity.this, gank.publishedAt, gank.url);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            MainActivity.this, meizhiView, "meizhi");

        try {
          ActivityCompat.startActivity(MainActivity.this, intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
          startActivity(intent);
        }
      }
    };
  }

  @OnClick(R.id.fab) public void onFabClick() {
    Snackbar.make(mFab, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public void onToolbarClick() {
    mRecyclerview.smoothScrollToPosition(0);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void setLoadingIndicator(boolean active) {
    mLayoutRefresh.setRefreshing(active);
  }

  @Override public void showMeizi(List<Gank> meizis) {
    mItems.addAll(meizis);
    mAdapter.notifyDataSetChanged();
  }

  @Override public void showLoadingMeiziError(Throwable throwable) {
    String errorMessage = ErrorMessageFactory.create(this, (Exception) throwable);
    ((GankApp) getApplication()).getApplicationComponent().getToastUtils().showLong(errorMessage);
  }
}


