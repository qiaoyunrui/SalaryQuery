package com.juhezi.salaryquery.data.source;

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

    }

    @Override
    public void getData(LoadDataCallback callback, String id) {

    }
}
