package com.juhezi.salaryquery.data.source;

import com.juhezi.salaryquery.data.SalaryData;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class Source implements DataSource {

    private static final String TAG = "Source";

    private static Source INSTANCE = null;
    private final DataSource mCacheDataSource;
    private final DataSource mLocalDataSource;
    private final DataSource mRemoteDataSource;

    private Source(DataSource mCacheDataSource, DataSource mLocalDataSource,
                   DataSource mRemoteDataSource) {
        this.mCacheDataSource = mCacheDataSource;
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    public static Source getInstance(DataSource mCacheDataSource, DataSource mLocalDataSource,
                                     DataSource mRemoteDataSource) {
        if (null == INSTANCE) {
            INSTANCE = new Source(mCacheDataSource, mLocalDataSource, mRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void refreshData(LoadDataCallback callback, String id) {
        mRemoteDataSource.refreshData(callback, id);
    }

    @Override
    public void getData(LoadDataCallback callback, String id) {
        getDataFromLocal(callback, id);
    }

    @Override
    public void saveData(String id, SalaryData data) {
        mCacheDataSource.saveData(id, data);
        mLocalDataSource.saveData(id, data);
    }

    private void getDataFromCache(final LoadDataCallback callback, final String id) {
        mCacheDataSource.getData(new LoadDataCallback() {
            @Override
            public void onDataLoaded(SalaryData salaryData) {   //从缓存中加载数据成功
                callback.onDataLoaded(salaryData);
            }

            @Override
            public void onDataNotAvailable() {
                getDataFromLocal(callback, id);
            }
        }, id);
    }

    private void getDataFromLocal(final LoadDataCallback callback, final String id) {
        mLocalDataSource.getData(new LoadDataCallback() {
            @Override
            public void onDataLoaded(SalaryData salaryData) {
                callback.onDataLoaded(salaryData);
            }

            @Override
            public void onDataNotAvailable() {
                mRemoteDataSource.getData(callback, id);
            }
        }, id);
    }
}
