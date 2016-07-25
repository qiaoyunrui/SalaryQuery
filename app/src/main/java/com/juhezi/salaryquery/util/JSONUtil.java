package com.juhezi.salaryquery.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class JSONUtil {

    private static final String TAG = "JSONUtil";

    public static <T> T string2json(String string, Class<T> clazz) {
        return new Gson().fromJson(string, clazz);
    }

}
