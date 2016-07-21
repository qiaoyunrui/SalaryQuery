package com.juhezi.salaryquery.query;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qiaoyunrui on 16-7-21.
 */
@Module
public class QueryPresenterModule {

    private final QueryContract.View mView;

    public QueryPresenterModule(QueryContract.View mView) {
        this.mView = mView;
    }

    @Provides
    QueryContract.View provideQueryPresenter() {
        return mView;
    }
}
