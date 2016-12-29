package com.jay.gankmvp.net;

import com.jay.gankmvp.data.MeizhiData;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jay on 16/12/21.
 */

public interface ApiService {

    @GET("data/福利/10/{page}")
    Flowable<MeizhiData> listMeizi(@Path("page") String page);

}
