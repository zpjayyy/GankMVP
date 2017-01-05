package com.jay.gankmvp.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jay on 16/12/22.
 */

public class Gank {


    /**
     * _id : 5859f7eb421aa9723a5a779d
     * createdAt : 2016-12-21T11:32:59.868Z
     * desc : 12-21
     * publishedAt : 2016-12-21T11:37:35.629Z
     * source : chrome
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/610dc034gw1fay98gt0ocj20u011hn24.jpg
     * used : true
     * who : 代码家
     */

    @SerializedName("_id")
    public String id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;
}
