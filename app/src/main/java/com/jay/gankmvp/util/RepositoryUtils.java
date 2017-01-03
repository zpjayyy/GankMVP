package com.jay.gankmvp.util;

import android.util.Log;

import com.jay.gankmvp.data.BaseData;
import com.jay.gankmvp.data.remote.ApiException;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by jay on 16/12/30.
 */

public class RepositoryUtils {

    private static final String TAG = RepositoryUtils.class.getSimpleName();


//    public static <T> Flowable<T> extractData(final Flowable<T> flowable) {
//
//    }

    public static class HttpResultFunc<T> implements Function<T, T> {
        @Override
        public T apply(T t) throws Exception {
            if (t instanceof BaseData) {
                if (((BaseData) t).error) {
                    throw new ApiException("get data failure");
                }

                return t;

            } else {
                throw new ClassCastException("class is not extend BaseData");
            }
        }
    }

    public static class ErrorConsumer implements Consumer<Throwable> {
        @Override
        public void accept(Throwable throwable) throws Exception {
            Log.d(TAG, throwable.getMessage());
        }
    }


}
