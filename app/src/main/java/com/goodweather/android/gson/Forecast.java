package com.goodweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-11-04.
 */

public class Forecast {
    public  String date;

    @SerializedName("cond")
    public More more;

    @SerializedName("tmp")
    public Temperature temperature;

    public class More {
        @SerializedName("txt_d")
        public String info;
    }

    public class Temperature{
        public String mix;
        public String min;
    }
}
