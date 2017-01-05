package com.jay.gankmvp.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * Created by jay on 17/1/4.
 */

public class ScrollAwareFABehavior extends FloatingActionButton.Behavior {

    private static final String TAG = ScrollAwareFABehavior.class.getSimpleName();

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;

    public ScrollAwareFABehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d(TAG, "dyConsumed = " + dyConsumed);

        if (dyConsumed > 0 && !mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            animateOut(child);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            animateIn(child);
        }
    }

    private void animateOut(final FloatingActionButton button) {
        ViewCompat.animate(button)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setInterpolator(INTERPOLATOR)
                .withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        ScrollAwareFABehavior.this.mIsAnimatingOut = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        ScrollAwareFABehavior.this.mIsAnimatingOut = false;
                        view.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        ScrollAwareFABehavior.this.mIsAnimatingOut = false;
                    }
                }).start();
    }

    private void animateIn(final FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);
        ViewCompat.animate(button)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setInterpolator(INTERPOLATOR)
                .withLayer()
                .setListener(null)
                .start();
    }

}
