package com.jay.gankmvp.data.remote;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.jay.gankmvp.R;

/**
 * Created by jay on 17/1/3.
 */

public class ErrorMessageFactory {

  private static final String TAG = ErrorMessageFactory.class.getSimpleName();

  private ErrorMessageFactory() {
  }

  public static String create(Context context, Exception exception) {
    if (!TextUtils.isEmpty(exception.getMessage())) {
      Log.e(TAG, exception.getMessage());
    }

    String message = context.getString(R.string.exception_message_generic);

    if (exception instanceof ApiException) {
      message = exception.getMessage();
    }

    return message;
  }
}
