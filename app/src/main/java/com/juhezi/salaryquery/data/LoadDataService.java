package com.juhezi.salaryquery.data;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.juhezi.salaryquery.data.source.DataSource;
import com.juhezi.salaryquery.data.source.Source;
import com.juhezi.salaryquery.data.source.cache.CacheDataSource;
import com.juhezi.salaryquery.data.source.local.LocalDataSource;
import com.juhezi.salaryquery.data.source.remote.RemoteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class LoadDataService extends Service {

    private static final String TAG = "LoadDataService";
    private CacheDataSource mCacheDataSource;
    private LocalDataSource mLocalDataSource;
    private RemoteDataSource mRemoteDataSource;
    private Source mSource;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LoadBinder(mSource);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCacheDataSource = CacheDataSource.getInstance();
        mLocalDataSource = LocalDataSource.getInstance(getApplicationContext());
        mRemoteDataSource = RemoteDataSource.getInstance(getApplicationContext());
        mSource = Source.getInstance(mCacheDataSource, mLocalDataSource, mRemoteDataSource);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class LoadBinder extends Binder {

        private final Source mSource;

        public LoadBinder(Source source) {
            this.mSource = source;
        }

        public void getData(final DataSource.LoadDataCallback callback, final String id) {
            new Thread() {
                @Override
                public void run() {
                    mSource.getData(callback, id);
                }
            }.start();
        }

        public void refresh(final DataSource.LoadDataCallback callback, final String id) {
            new Thread() {
                @Override
                public void run() {
                    mSource.refreshData(callback, id);
                }
            }.start();
        }

    }

}
