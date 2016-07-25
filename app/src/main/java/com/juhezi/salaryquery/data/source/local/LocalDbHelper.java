package com.juhezi.salaryquery.data.source.local;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qiaoyunrui on 16-7-25.
 */
public class LocalDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "LocalDbHelper";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "queryDatas.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String BOOLEAN_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataPersistenceContract.DataEntry.TABLE_NAME + " (" +
                    DataPersistenceContract.DataEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    DataPersistenceContract.DataEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    DataPersistenceContract.DataEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    DataPersistenceContract.DataEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    DataPersistenceContract.DataEntry.COLUMN_NAME_COMPLETED + BOOLEAN_TYPE +
                    " )";

    public LocalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}