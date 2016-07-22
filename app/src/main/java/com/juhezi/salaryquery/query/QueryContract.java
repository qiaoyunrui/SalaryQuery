package com.juhezi.salaryquery.query;

import com.juhezi.salaryquery.BasePresenter;
import com.juhezi.salaryquery.BaseView;
import com.juhezi.salaryquery.data.SalaryDetail;
import com.juhezi.salaryquery.login.LoginContract;

import java.util.List;

/**
 * Created by qiaoyunrui on 16-7-21.
 */
public interface QueryContract {

    interface Presenter extends BasePresenter {

        void logOut();  //退出登录

        void refresh(); //刷新数据

    }

    interface View extends BaseView<QueryContract.Presenter> {

        void turn2LoinActivity();   //回到登录页面

        void showErrorDialog(); //显示获取数据失败对话框

        void showProgressBar(); //显示进度条

        void hideProgressBar();  //隐藏进度条

        void enableFab();

        void unEnableFab();

        void updateRecyclerView(List<SalaryDetail> dataList);  //更新显示的数据
    }

}
