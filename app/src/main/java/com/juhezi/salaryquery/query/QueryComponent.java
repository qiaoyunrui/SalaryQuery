package com.juhezi.salaryquery.query;

import com.juhezi.salaryquery.ApplicationModule;

import dagger.Component;

/**
 * Created by qiaoyunrui on 16-7-21.
 */
@Component(modules = {QueryPresenterModule.class, ApplicationModule.class})
public interface QueryComponent {

    void inject(QueryActivity activity);

}
