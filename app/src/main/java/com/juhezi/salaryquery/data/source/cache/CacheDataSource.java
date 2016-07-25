package com.juhezi.salaryquery.data.source.cache;

import android.util.LruCache;

import com.juhezi.salaryquery.data.SalaryData;
import com.juhezi.salaryquery.data.source.DataSource;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class CacheDataSource implements DataSource {
    private static final String TAG = "CacheDataSource";

    private static CacheDataSource INSTANCE = null;

    private LruCache<String, SalaryData> dataLruCache;

    private int maxMemory = (int) Runtime.getRuntime().maxMemory() / 8 / 1024;  //单位为KB

    public static CacheDataSource getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new CacheDataSource();
        }
        return INSTANCE;
    }

    private CacheDataSource() {
        dataLruCache = new LruCache<>(maxMemory);
    }

    @Override
    public void refreshData(LoadDataCallback callback, String id) {
    }

    @Override
    public void getData(LoadDataCallback callback, String id) {
        SalaryData salaryData = dataLruCache.get(id);
        if (null == salaryData) {    //没有缓存
            callback.onDataNotAvailable();
        } else {
            callback.onDataLoaded(salaryData);
        }
    }

    @Override
    public void saveData(String id, SalaryData data) {
        dataLruCache.put(id, data);
    }
}
