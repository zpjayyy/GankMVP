package com.jay.gankmvp.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jay on 16/12/29.
 */

public class MeizhiItemDecoration extends RecyclerView.ItemDecoration {

  private int space;

  public MeizhiItemDecoration(int space) {
    this.space = space;
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    int position = parent.getChildLayoutPosition(view);
    if (position % 2 == 0) {
      outRect.right = space;
    }
  }
}

