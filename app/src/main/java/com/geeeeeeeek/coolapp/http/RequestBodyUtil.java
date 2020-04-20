package com.geeeeeeeek.coolapp.http;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/16
 * Time: 4:34 PM
 */
public class RequestBodyUtil {

    public static RequestBody getBody(Map<String, Object> map) {
        return RequestBody.create(MediaType.parse("Content-Type, application/json"), new JSONObject(map).toString());
    }
}
