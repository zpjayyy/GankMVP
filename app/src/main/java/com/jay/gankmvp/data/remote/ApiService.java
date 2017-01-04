package com.jay.gankmvp.data.remote;

import com.jay.gankmvp.data.MeizhiData;
import com.jay.gankmvp.data.entity.Meizhi;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jay on 16/12/21.
 */

public interface ApiService {

    @GET("data/福利/10/{page}")
    Flowable<MeizhiData> listMeizi(@Path("page") int page);

}
