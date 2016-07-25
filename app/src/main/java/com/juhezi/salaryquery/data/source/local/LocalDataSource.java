package com.juhezi.salaryquery.data.source.local;

import android.content.Context;

import com.juhezi.salaryquery.data.SalaryData;
import com.juhezi.salaryquery.data.source.DataSource;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class LocalDataSource implements DataSource {
    private static LocalDataSource INSTANCE = null;
    private LocalDbHelper mLocalDbHelper;
    private Context mContext;

    public static LocalDataSource getInstance(Context context) {
        if (null == INSTANCE) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }

    private LocalDataSource(Context context) {
        this.mContext = context;
        mLocalDbHelper = new LocalDbHelper(context);
    }

    @Override
    public void refreshData(LoadDataCallback callback, String id) {

    }

    @Override
    public void getData(LoadDataCallback callback, String id) {

    }

    @Override
    public void saveData(String id, SalaryData data) {

    }
}
