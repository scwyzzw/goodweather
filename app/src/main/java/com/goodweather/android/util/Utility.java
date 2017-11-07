package com.goodweather.android.util;

import android.text.TextUtils;

import com.goodweather.android.db.City;
import com.goodweather.android.db.County;
import com.goodweather.android.db.Province;
import com.goodweather.android.gson.Weather;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;
import org.w3c.dom.Text;

/**
 * Created by Administrator on 2017-10-21.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProviceResponse(String response) {
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province= new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }return false;
    }

    /**
     * * 解析和处理服务器返回的市级数据
     * */
    public static boolean handleCityResponse(String response,int provinceId) {
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCity = new JSONArray(response);
                for (int i=0;i<allCity.length();i++){
                    JSONObject cityObject = allCity.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }return false;
    }

    /**
     * 解析和处理服务器返回县级数据
     */
    public static boolean handleCountResponse(String response,int cityId) {
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCount = new JSONArray(response);
                for (int i=0;i<allCount.length();i++){
                    JSONObject countObject = allCount.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countObject.getString("name"));
                    county.setWeatherId(countObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }return false;
    }

    /**
     * 将返回的JSON数据解析成 Weqther 实体类
     */
    public static Weather handliWeatherResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONArray(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
   }

}
