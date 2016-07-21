package com.juhezi.salaryquery.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.juhezi.juprogressbar.View.JuProgressbar;
import com.juhezi.salaryquery.Config;
import com.juhezi.salaryquery.R;
import com.juhezi.salaryquery.query.QueryActivity;

/**
 * Created by qiaoyunrui on 16-7-20.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private static final String TAG = "LoginFragment";

    private View rootView;
    private TextInputLayout mTilUsername;
    private TextInputLayout mTilPasswd;
    private AppCompatButton mAcbLogin;
    private JuProgressbar mJpProgressbar;

    private LoginContract.Presenter mPresenter;

    private Intent mIntent;
    private AlertDialog.Builder builder;

    private String username;
    private String passwd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_frag, container, false);
        init();
        initView();
        initEvent();
        return rootView;
    }

    private void init() {
        builder = new AlertDialog.Builder(getContext());
        builder.setMessage(Config.DIALOG_MESSAGE)
                .setTitle(Config.DIALOG_TITLE)
                .setPositiveButton(Config.OK, null);
    }

    private void initView() {
        mTilUsername = (TextInputLayout) rootView.findViewById(R.id.til_username);
        mTilPasswd = (TextInputLayout) rootView.findViewById(R.id.til_passwd);
        mAcbLogin = (AppCompatButton) rootView.findViewById(R.id.acb_login);
        mJpProgressbar = (JuProgressbar) rootView.findViewById(R.id.jp_progressbar);
    }

    private void initEvent() {
        mAcbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputMessage();
                mPresenter.login(username, passwd);
            }
        });
        setTextWatcher(mTilUsername);
        setTextWatcher(mTilPasswd);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public void showProgressBar() {
        mJpProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mJpProgressbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorDialog() {
        builder.create().show();    //显示密码错误对话框
    }

    @Override
    public void enableButton() {
        mAcbLogin.setClickable(true);
    }

    @Override
    public void unenableButton() {
        mAcbLogin.setClickable(false);
    }

    @Override
    public void turn2QueryActivity() {
        mIntent = new Intent(getActivity(), QueryActivity.class);
        startActivity(mIntent);
        getActivity().finish();
    }

    @Override
    public void setUsername(String username) {
        mTilUsername.getEditText().setText(username);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    void setTextWatcher(final TextInputLayout textInputLayout) {
        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < Config.INPUT_LENGTH) {   //NO
                    textInputLayout.setError(Config.INPUT_ERROR);
                    textInputLayout.setErrorEnabled(true);
                    unenableButton();
                } else {    //TRUE
                    textInputLayout.setErrorEnabled(false);
                    enableButton();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 获取输入的信息
     */
    void getInputMessage() {
        username = mTilUsername.getEditText().getText().toString().trim();
        passwd = mTilPasswd.getEditText().getText().toString().trim();
    }
}
