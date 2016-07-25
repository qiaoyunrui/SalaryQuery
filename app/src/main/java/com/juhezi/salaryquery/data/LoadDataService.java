package com.juhezi.salaryquery.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class LoadDataService extends Service {

    private static final String TAG = "LoadDataService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, Thread.currentThread().getName());
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
