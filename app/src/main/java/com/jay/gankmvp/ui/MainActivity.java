package com.jay.gankmvp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jay.gankmvp.GankApp;
import com.jay.gankmvp.R;
import com.jay.gankmvp.data.entity.Meizhi;
import com.jay.gankmvp.presenter.MainPresenter;
import com.jay.gankmvp.presenter.contract.MainContract;
import com.jay.gankmvp.ui.base.ToolbarActivity;
import com.jay.gankmvp.ui.provide.MeizhiViewProvider;
import com.jay.gankmvp.widget.MeizhiItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends ToolbarActivity implements MainContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.layout_refresh)
    SwipeRefreshLayout mLayoutRefresh;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    MainPresenter mMainPresenter;

    MultiTypeAdapter mAdapter;
    Items mItems;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mMainPresenter = DaggerMainActivityComponent.builder()
                .apiComponent(((GankApp) getApplication()).getApiComponent())
                .mainPresenterModule(new MainPresenterModule(this))
                .build().getMainPresenter();

        mMainPresenter.loadMeizis(false);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.unsubscribe();
    }

    private void initView() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);

        mRecyclerview.setLayoutManager(manager);

        int space = getResources().getDimensionPixelSize(R.dimen.meizhi_item_space);
        mRecyclerview.addItemDecoration(new MeizhiItemDecoration(space));

        mItems = new Items();
        mAdapter = new MultiTypeAdapter(mItems);
        mAdapter.register(Meizhi.class, new MeizhiViewProvider());

        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    public void setLoadingIndicator(boolean active) {
        mLayoutRefresh.setRefreshing(active);
    }

    @Override
    public void showMeizi(List<Meizhi> meizis) {
        mItems.addAll(meizis);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingMeiziError() {

    }

    @Override
    public void showNoMeizis() {

    }

}

