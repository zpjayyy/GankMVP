package com.jay.gankmvp.data;

import com.google.gson.annotations.SerializedName;
import com.jay.gankmvp.data.entity.Gank;

import java.util.List;

/**
 * Created by jay on 17/1/5.
 */

public class DailyGankData extends BaseData {

  public List<String> category;

  public Results results;

  public class Results {
    @SerializedName("iOS") public List<Gank> iOSList;
    @SerializedName("Android") public List<Gank> androidList;
    @SerializedName("瞎推荐") public List<Gank> recommendList;
    @SerializedName("前端") public List<Gank> webList;
    @SerializedName("福利") public List<Gank> boonList;
    @SerializedName("休息视频") public List<Gank> videoList;
    @SerializedName("App") public List<Gank> appList;
    @SerializedName("拓展资源") public List<Gank> extraList;
  }
}
