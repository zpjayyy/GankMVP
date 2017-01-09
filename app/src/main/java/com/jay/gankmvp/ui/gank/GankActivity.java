package com.jay.gankmvp.ui.gank;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jay.gankmvp.GankApp;
import com.jay.gankmvp.R;
import com.jay.gankmvp.data.entity.Gank;
import com.jay.gankmvp.data.remote.ErrorMessageFactory;
import com.jay.gankmvp.injection.component.DaggerGankActivityComponent;
import com.jay.gankmvp.injection.module.GankActivityModule;
import com.jay.gankmvp.provide.CategoryViewProvider;
import com.jay.gankmvp.provide.GankViewProvider;
import com.jay.gankmvp.ui.base.ToolbarActivity;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by jay on 17/1/5.
 */

public class GankActivity extends ToolbarActivity implements GankContract.View {

  private static final String EXTRA_DATE = "extra_date";
  private static final String EXTRA_MEIZHI_URL = "extra_meizhi_url";

  @BindView(R.id.recyclerview_gank) RecyclerView mRecyclerviewGank;
  @BindView(R.id.image_meizhi) SimpleDraweeView mImageMeizhi;

  String mMeizhiUrl;
  Date mDate;
  GankPresenter mGankPresenter;

  MultiTypeAdapter mAdapter;
  Items mItems;

  int mYear;
  int mMonth;
  int mDay;

  public static Intent newIntent(Context context, Date date, String meizhiUrl) {
    Intent intent = new Intent(context, GankActivity.class);
    intent.putExtra(EXTRA_DATE, date);
    intent.putExtra(EXTRA_MEIZHI_URL, meizhiUrl);
    return intent;
  }

  @Override protected int provideContentViewId() {
    return R.layout.activity_gank;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    parseIntent();
    initCalendarAndLoadData();

    mGankPresenter = DaggerGankActivityComponent.builder()
        .apiComponent(((GankApp) getApplication()).getApiComponent())
        .gankActivityModule(new GankActivityModule(this))
        .build()
        .getGankPresenter();


    initView();

  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mGankPresenter.unsubscribe();
  }

  @Override public boolean canBack() {
    return true;
  }

  private void parseIntent() {
    mDate = (Date) getIntent().getSerializableExtra(EXTRA_DATE);
    mMeizhiUrl = getIntent().getStringExtra(EXTRA_MEIZHI_URL);
  }

  private void initCalendarAndLoadData() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(mDate);

    mYear = calendar.get(Calendar.YEAR);
    mMonth = calendar.get(Calendar.MONTH) + 1;
    mDay =  calendar.get(Calendar.DAY_OF_MONTH);

  }

  private void initView() {

    setTitle(mYear + "/" + mMonth + "/" + mDay);

    mImageMeizhi.setImageURI(Uri.parse(mMeizhiUrl));

    final LinearLayoutManager manager = new LinearLayoutManager(this);
    mRecyclerviewGank.setLayoutManager(manager);

    mItems = new Items();
    mAdapter = new MultiTypeAdapter(mItems);

    mAdapter.register(String.class, new CategoryViewProvider());
    mAdapter.register(Gank.class, new GankViewProvider());

    mRecyclerviewGank.setAdapter(mAdapter);

    mGankPresenter.loadDailyGankData(mYear, mMonth, mDay);
  }

  @Override public void setLoadingIndicator(boolean active) {

  }

  @Override public void showGank(List<Object> gankList) {
    mItems.addAll(gankList);
    mAdapter.notifyDataSetChanged();
  }

  @Override public void showLoadingGankError(Throwable throwable) {
    String errorMessage = ErrorMessageFactory.create(this, (Exception) throwable);
    ((GankApp) getApplication()).getApplicationComponent().getToastUtils().showLong(errorMessage);
  }
}
