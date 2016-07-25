package com.juhezi.salaryquery.data.source;

import com.juhezi.salaryquery.data.SalaryData;
import com.juhezi.salaryquery.data.SalaryDetail;

import java.util.List;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public interface DataSource {

    interface LoadDataCallback {
        //数据加载成功
        void onDataLoaded(SalaryData salaryData);

        //数据加载失败
        void onDataNotAvailable();
    }

    /**
     * 刷新数据，获取远程数据
     *
     * @param callback
     * @param id
     */
    void refreshData(LoadDataCallback callback, String id);

    /**
     * 获取数据，先缓存，后数据库，最后远程
     *
     * @param callback
     * @param id
     */
    void getData(LoadDataCallback callback, String id);

    void saveData(String id, SalaryData data);
}
