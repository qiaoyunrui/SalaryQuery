package com.juhezi.salaryquery.data.source.local;

import com.juhezi.salaryquery.data.SalaryData;
import com.juhezi.salaryquery.data.source.DataSource;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class LocalDataSource implements DataSource {
    private static LocalDataSource INSTANCE = null;

    public static LocalDataSource getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new LocalDataSource();
        }
        return INSTANCE;
    }

    private LocalDataSource() {
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
