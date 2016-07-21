package com.juhezi.salaryquery.login;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.juhezi.salaryquery.ApplicationModule;
import com.juhezi.salaryquery.R;

import java.util.logging.Logger;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Inject
    LoginPresenter mLoginPresenter;

    LoginFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        mFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.rn_root_act);

        if (null == mFragment) {
            mFragment = new LoginFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.rn_root_act, mFragment);
            transaction.commit();
        }

        DaggerLoginComponent.builder()
                .loginPresenterModule(new LoginPresenterModule(mFragment))
                .applicationModule(new ApplicationModule(LoginActivity.this))
                .build()
                .inject(this);

    }
}
