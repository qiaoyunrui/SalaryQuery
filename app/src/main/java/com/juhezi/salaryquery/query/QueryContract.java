package com.juhezi.salaryquery.query;

import com.juhezi.salaryquery.BasePresenter;
import com.juhezi.salaryquery.BaseView;
import com.juhezi.salaryquery.login.LoginContract;

/**
 * Created by qiaoyunrui on 16-7-21.
 */
public interface QueryContract {

    interface Presenter extends BasePresenter {


    }

    interface View extends BaseView<LoginContract.Presenter> {



    }

}
