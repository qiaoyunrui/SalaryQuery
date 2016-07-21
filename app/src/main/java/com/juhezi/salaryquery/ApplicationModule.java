package com.juhezi.salaryquery;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qiaoyunrui on 16-7-20.
 */
@Module
public final class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

}
