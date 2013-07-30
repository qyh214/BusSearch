package com.chenwg.bussearch.service;

import android.os.Bundle;
import cn.open189.api.http.Callback;
import cn.open189.api.http.HttpHelper;
import cn.open189.api.http.requestlistener.DefaultRequestListener;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by della on 13-7-29.
 */
public class SearchService {
    /**
     * 公交站点url
     */
    public static String busSiteNameInfoUrl = "http://api.189.cn/v2/besttone/getBusSiteNameInfo";
    /**
     * 公交线路查询url
     */
    public static String busRouteInfoUrl = "http://api.189.cn/v2/besttone/getBusRouteInfo";

    /**
     * 公交站点名称查询线路
     * @param url
     * @param params
     * @param callback
     */
    public static void getBusSiteNameInfo(String url,Bundle params,final Callback callback){
        HttpHelper.doPost(url, null, params, new DefaultRequestListener(callback));
    }

    /**
     * 公交线路查询
     * @param url
     * @param params
     * @param callback
     */
    public static void getBusRouteInfo(String url,Bundle params,final Callback callback){
        HttpHelper.doPost(url, null, params, new DefaultRequestListener(callback));
    }
}

























