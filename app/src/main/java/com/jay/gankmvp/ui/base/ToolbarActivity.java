package com.jay.gankmvp.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.jay.gankmvp.R;

/**
 * Created by jay on 16/12/29.
 */

public abstract class ToolbarActivity extends BaseActivity {

    abstract protected int provideContentViewId();

    public void onToolbarClick() {
    }


    protected AppBarLayout mAppbar;
    protected Toolbar mToolbar;
    protected boolean mIsHidden = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());

        mAppbar = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar == null || mAppbar == null) {
            throw new IllegalStateException(
                    "The subclass of ToobarActivity must contain a toolbar"
            );
        }

        setSupportActionBar(mToolbar);
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (canBack()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) actionBar.setDisplayShowHomeEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            mAppbar.setElevation(10.6f);
        }

    }

    public boolean canBack() {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void setAppBarAlpha(float alpha) {
        mAppbar.setAlpha(alpha);
    }

    protected void hideOrShowToobar() {
        mAppbar.animate()
                .translationY(mIsHidden ? 0 : -mAppbar.getHeight())
                .setInterpolator(new DecelerateInterpolator())
                .start();
        mIsHidden = !mIsHidden;
    }
}
