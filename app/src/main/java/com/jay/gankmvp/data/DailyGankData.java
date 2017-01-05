package com.jay.gankmvp.data;

import com.google.gson.annotations.SerializedName;
import com.jay.gankmvp.data.entity.Gank;

import java.util.List;

/**
 * Created by jay on 17/1/5.
 */

public class DailyGankData extends BaseData {

  public List<String> category;

  public class results {
    @SerializedName("iOS") List<Gank> iOSList;
    @SerializedName("Android") List<Gank> androidList;
    @SerializedName("瞎推荐") List<Gank> recommendList;
    @SerializedName("拓展资源") List<Gank> extraList;
    @SerializedName("福利") List<Gank> boonList;
    @SerializedName("休息视频") List<Gank> videoList;
  }
}
