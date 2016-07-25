package com.juhezi.salaryquery.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class DataPersistenceContract {

    private static final String TAG = "DataPersistenceContract";

    public DataPersistenceContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class DataEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_COMPLETED = "completed";
    }

}
