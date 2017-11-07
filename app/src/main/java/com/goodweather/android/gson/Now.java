package com.goodweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-11-04.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}
