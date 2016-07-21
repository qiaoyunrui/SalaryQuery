package com.juhezi.salaryquery.login;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 提供View
 *
 * Created by qiaoyunrui on 16-7-20.
 */
@Module
public class LoginPresenterModule {

    private final LoginContract.View mView;

    public LoginPresenterModule(LoginContract.View mView) {
        this.mView = mView;
    }

    @Provides
    LoginContract.View provideLoginPresenter() {
        return mView;
    }
}
