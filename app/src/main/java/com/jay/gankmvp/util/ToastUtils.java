package com.jay.gankmvp.util;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by jay on 17/1/3.
 */

public class ToastUtils {

  Context mContext;

  @Inject public ToastUtils(Context context) {
    this.mContext = context;
  }

  public void showShort(int stringResId) {
    Toast.makeText(mContext, stringResId, Toast.LENGTH_SHORT).show();
  }

  public void showShort(String stringToast) {
    Toast.makeText(mContext, stringToast, Toast.LENGTH_SHORT).show();
  }

  public void showLong(int stringResId) {
    Toast.makeText(mContext, stringResId, Toast.LENGTH_SHORT).show();
  }

  public void showLong(String stringToast) {
    Toast.makeText(mContext, stringToast, Toast.LENGTH_SHORT).show();
  }
}
