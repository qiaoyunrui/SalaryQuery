package com.juhezi.salaryquery.login;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.juhezi.salaryquery.Config;
import com.juhezi.salaryquery.data.LoginData;
import com.juhezi.salaryquery.data.params.LoginParam;

import javax.inject.Inject;

/**
 * Created by qiaoyunrui on 16-7-20.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    private static final String TAG = "LoginPresenter";

    private RequestQueue mQueue;

    private Context mContext;

    private SharedPreferences mSharedPreferences;

    private LoginData mLoginData;

    private String userinfo;

    @Inject
    LoginPresenter(LoginContract.View loginView, Context context) {
        this.mLoginView = loginView;
        this.mContext = context;
        loginView.setPresenter(this);
        mQueue = Volley.newRequestQueue(mContext);
    }

    @Override
    public void loginUnsuccessfully() {
        mLoginView.hideProgressBar();
        mLoginView.showErrorDialog();
        mLoginView.enableButton();
    }

    @Override
    public void loginSuccessfully() {
        saveUserInfo(userinfo);
        updateUserInfo();
        mLoginView.turn2QueryActivity();
    }

    @Override
    public void login(String username, String passwd) {
        saveUsername(username); //保存用户名
        mLoginView.showProgressBar();
        mLoginView.unenableButton();
        queryRemoteData(username, passwd);
    }

    @Override
    public void start() {

        if (getTheLogState()) {
            if (!isOutDate()) {  //登录过期
                mLoginView.turn2QueryActivity();
                return;
            }
        }
        mLoginView.setUsername(getUsername());
        mLoginView.unenableButton();
        mLoginView.hideProgressBar();
    }

    /**
     * 查询远程数据
     *
     * @param username
     * @param passwd
     */

    private void queryRemoteData(String username, String passwd) {
        LoginParam param =
                new LoginParam(Config.LOGIN_METHOD_NAME, Config.COMPANY_NAME, username, passwd);
        StringRequest request = new StringRequest(Config.URL_PART1 + param + Config.URL_PART2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.e(TAG, response);
                        userinfo = response;
                        mLoginView.hideProgressBar();
                        decodeResult(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "获取信息失败");
                loginUnsuccessfully();
            }
        });
        mQueue.add(request);
        mQueue.start();
    }

    /**
     * JSON数据格式存放在sharepreference
     *
     * @param userinfo
     */
    private void saveUserInfo(String userinfo) {
        if (null == mSharedPreferences) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(Config.USER_STATE_TAG, true);     //储存用户登录状态
        editor.putString(Config.USER_INFO_TAG, userinfo);   //储存用户详细信息
        editor.commit();
    }

    /**
     * 更新的登录时间
     */
    private void updateUserInfo() {
        if (null == mSharedPreferences) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(Config.USER_LOGIN_TIME, System.currentTimeMillis());  //更新当前时间
        editor.commit();
    }

    //判断登录状态是否过期
    public boolean isOutDate() {
        if (null == mSharedPreferences) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE,
                    Context.MODE_PRIVATE);
        }
        long currentTime = System.currentTimeMillis();
        long lastTime = mSharedPreferences.getLong(Config.USER_LOGIN_TIME, currentTime - Config.DEADLINE - 100);
        if (currentTime - lastTime > Config.DEADLINE) {
            return true;    //过期
        } else {
            return false;   //未过期
        }
    }

    //缓存用户名，用于显示输入框
    public void saveUsername(String username) {
        if (null == mSharedPreferences) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Config.USERNAME_TAG, username);
        editor.commit();
    }

    //获取缓存的用户名
    public String getUsername() {
        if (null == mSharedPreferences) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE,
                    Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getString(Config.USERNAME_TAG, "");
    }

    //解析数据
    private void decodeResult(String result) {
        Gson gson = new Gson();
        mLoginData = gson.fromJson(result, LoginData.class);
        if (mLoginData.getCode().trim().equals(Config.LOGIN_SUCCESS_CODE)) {     //登录成功
            loginSuccessfully();
        } else {    //登录失败
            loginUnsuccessfully();
        }
    }

    /**
     * 获取登录状态
     */
    private boolean getTheLogState() {
        if (null == mSharedPreferences) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE,
                    Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getBoolean(Config.USER_STATE_TAG, false);
    }
}
