package com.juhezi.salaryquery.query;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.juhezi.salaryquery.ApplicationModule;
import com.juhezi.salaryquery.Config;
import com.juhezi.salaryquery.R;

import javax.inject.Inject;

/**
 * Created by qiaoyunrui on 16-7-20.
 */
public class QueryActivity extends AppCompatActivity {

    private QueryFragment mFragment;

    @Inject
    QueryPresenter mPresenter;

    private static final String TAG = "QueryActivity";

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

        mFragment = (QueryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.ll_root);

        if (null == mFragment) {
            mFragment = new QueryFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.ll_root, mFragment);
            transaction.commit();
        }

        DaggerQueryComponent.builder()
                .queryPresenterModule(new QueryPresenterModule(mFragment))
                .applicationModule(new ApplicationModule(QueryActivity.this))
                .build()
                .inject(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.query_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_query_toolbar_logout) { //退出登录
            mPresenter.logOut();
            Log.i(TAG, "Logout");
        }
        return super.onOptionsItemSelected(item);
    }
}
