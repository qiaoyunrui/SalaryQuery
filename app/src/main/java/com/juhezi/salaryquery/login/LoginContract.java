package com.juhezi.salaryquery.login;

import com.juhezi.salaryquery.BasePresenter;
import com.juhezi.salaryquery.BaseView;

/**
 * Created by qiaoyunrui on 16-7-20.
 */
public interface LoginContract {

    /**
     * 一切与逻辑有关的都要放在这里
     */
    interface Presenter extends BasePresenter {
        void loginUnsuccessfully();

        void loginSuccessfully();

        void login(String username, String passwd);

        public boolean isOutDate();
    }

    interface View extends BaseView<Presenter> {

        void showProgressBar();

        void hideProgressBar();

        //登录失败显示对话框
        void showErrorDialog();

        void enableButton();

        void unenableButton();

        void turn2QueryActivity();

        void setUsername(String username);
    }

}
