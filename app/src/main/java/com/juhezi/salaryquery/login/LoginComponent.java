package com.juhezi.salaryquery.login;

import com.juhezi.salaryquery.ApplicationModule;

import dagger.Component;

/**
 * Created by qiaoyunrui on 16-7-20.
 */
@Component(modules = {LoginPresenterModule.class, ApplicationModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);

}
