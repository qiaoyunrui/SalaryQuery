package com.juhezi.salaryquery.query;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.juhezi.salaryquery.Config;
import com.juhezi.salaryquery.R;

/**
 * Created by qiaoyunrui on 16-7-20.
 */
public class QueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_act);

        Toolbar mTbQuery = (Toolbar) findViewById(R.id.tb_query);
        setSupportActionBar(mTbQuery);
        ActionBar ab = getSupportActionBar();
        /*ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.toolbar_ic); //修改返回键*/
        ab.setIcon(R.drawable.toolbar_ic);
        ab.setTitle(Config.TOOLBAR_TITLE);
    }
}
