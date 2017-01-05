package com.jay.gankmvp.data.remote;

import com.jay.gankmvp.config.Constant;
import com.jay.gankmvp.data.DailyGankData;
import com.jay.gankmvp.data.GankData;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jay on 16/12/21.
 */

public interface ApiService {

  @GET("data/福利/" + Constant.REQUEST_NUM + "/{page}") Flowable<GankData> listMeizi(
      @Path("page") int page);

  @GET("data/休息视频/" + Constant.REQUEST_NUM + "/{page}") Flowable<GankData> listVideo(
      @Path("page") int page);

  @GET("day/{year}/{month}/{day}") Flowable<DailyGankData> listGank(@Path("year") int year,
      @Path("month") int month, @Path("day") int day);
}
