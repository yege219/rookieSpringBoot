package com.rookie.utils;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rookie on 2018/1/18.
 * 此类用于网络请求时client对cookie的管理
 */
public class LocalCookieJar implements CookieJar {

    List<Cookie> cookies;

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        this.cookies = list;
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        if (cookies != null) {
            return cookies;
        }
        return new ArrayList<Cookie>();
    }
}
