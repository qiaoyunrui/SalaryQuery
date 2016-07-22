package com.juhezi.salaryquery.query;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.juhezi.salaryquery.Config;
import com.juhezi.salaryquery.data.LoginData;
import com.juhezi.salaryquery.data.SalaryDetail;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by qiaoyunrui on 16-7-21.
 */
public class QueryPresenter implements QueryContract.Presenter {

    private static final String TAG = "QueryPresenter";

    private QueryContract.View mQueryView;

    private Context mContext;

    private SharedPreferences mSharedPreferences;

    private LoginData userData; //用户登陆时的数据

    private List<SalaryDetail> dataList = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x123:
                    mQueryView.updateRecyclerView(dataList);
                    mQueryView.hideProgressBar();
                    mQueryView.enableFab();
                    break;
                default:
            }
        }
    };

    @Inject
    public QueryPresenter(QueryContract.View queryView, Context context) {
        mQueryView = queryView;
        this.mContext = context;
        mQueryView.setPresenter(this);
    }

    @Override
    public void start() {
        mQueryView.showProgressBar();
        mQueryView.unEnableFab();
        getLogData();
        loadCacheDataFirst();
    }

    @Override
    public void logOut() {
        modifyTheLogState();
        mQueryView.turn2LoinActivity();
    }

    @Override
    public void refresh() {

    }

    /**
     * 获取登录时存储的数据
     */
    private void getLogData() {
        if (null == mSharedPreferences) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE,
                    Context.MODE_PRIVATE);
        }
        String data = mSharedPreferences.getString(Config.USER_INFO_TAG, "");
        userData = new Gson().fromJson(data, LoginData.class);
    }

    private List<SalaryDetail> decodeLoginData2List(LoginData loginData) {
        List<SalaryDetail> list = new ArrayList<>();
        if (null != loginData) {
            list.add(new SalaryDetail("姓名", loginData.
                    getSuccessResult().getUser().get(0).getUSERDESC()));
            list.add(new SalaryDetail("公司", loginData.
                    getSuccessResult().getTenant().get(0).getTENANTDESC()));
        }
        return list;
    }

    /**
     * 修改登录信息
     */
    private void modifyTheLogState() {
        if (null == mSharedPreferences) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(Config.USER_STATE_TAG, false);     //储存用户登录状态
        editor.commit();
    }

    /**
     * 优先加载缓存数据，即第一次加载数据
     */
    private void loadCacheDataFirst() {
        new Thread() {
            @Override
            public void run() {
                dataList = decodeLoginData2List(userData);
                mHandler.sendEmptyMessage(0x123);
            }
        }.start();
    }

    /**
     * 加载服务器数据
     */
    private void loadRomateData() {

    }

}
