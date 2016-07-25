package com.juhezi.salaryquery.data.source.remote;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.juhezi.salaryquery.Config;
import com.juhezi.salaryquery.data.SalaryData;
import com.juhezi.salaryquery.data.source.DataSource;
import com.juhezi.salaryquery.util.JSONUtil;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class RemoteDataSource implements DataSource {


    private static RemoteDataSource INSTANCE = null;

    private RequestQueue mQueue;

    private Context mContext;

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }
        return INSTANCE;
    }

    private RemoteDataSource(Context context) {
        this.mContext = context;
        mQueue = Volley.newRequestQueue(mContext);
    }

    @Override
    public void refreshData(final LoadDataCallback callback, String id) {
        StringRequest request = new StringRequest(Config.URL_PART1 + Config.URL_PART2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SalaryData data = JSONUtil.string2json(response, SalaryData.class);
                        callback.onDataLoaded(data);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onDataNotAvailable();
            }
        });
        mQueue.add(request);
        mQueue.start();
    }

    @Override
    public void getData(LoadDataCallback callback, String id) {
        refreshData(callback, id);
    }

    @Override
    public void saveData(String id, SalaryData data) {

    }
}
