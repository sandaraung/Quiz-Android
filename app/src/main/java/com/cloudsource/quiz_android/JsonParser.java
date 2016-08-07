package com.cloudsource.quiz_android;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by ayemyathu on 8/7/16.
 */

public class JsonParser {
//    private static final String MAIN_URL = "http://192.168.100.9:3000/";
    private static Response response;
    public static final String TAG = "TAG";

    public static JSONObject getData(String url) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            response = okHttpClient.newCall(request).execute();
            String responseBody = response.body().string();
            JSONObject jo = new JSONObject(responseBody);
            return jo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
