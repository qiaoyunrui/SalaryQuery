package com.juhezi.salaryquery.query;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.juhezi.juprogressbar.View.JuProgressbar;
import com.juhezi.salaryquery.Config;
import com.juhezi.salaryquery.R;
import com.juhezi.salaryquery.data.SalaryDetail;
import com.juhezi.salaryquery.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaoyunrui on 16-7-21.
 */
public class QueryFragment extends Fragment implements QueryContract.View {

    private static final String TAG = "QueryFragment";

    private View rootView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FloatingActionButton mFabRefresh;   //用于刷新
    private JuProgressbar mJpProgressbar;
    private AlertDialog.Builder mBuilder;
    private QueryAdapter adapter;

    private QueryContract.Presenter mPresenter;

    private List<SalaryDetail> dataList = new ArrayList<>();
    private Intent intent;

    private ObjectAnimator animator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.query_frag, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);
        mFabRefresh = (FloatingActionButton) rootView.findViewById(R.id.fab_refresh);
        mJpProgressbar = (JuProgressbar) rootView.findViewById(R.id.jp_progressbar);
        configRecyclerView();
        initDialog();
        initEvent();
        setAnimForFab();
        return rootView;
}

    private void initDialog() {
        mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle(Config.DIALOG_NET_ERROR_TITLE)
                .setMessage(Config.DIALOG_NET_ERROR_MESSAGE)
                .setPositiveButton(Config.OK, null);
    }


    private void initEvent() {
        mFabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playFabAnim();
            }
        });
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
    public void setPresenter(QueryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 为fab设置动画
     */
    public void setAnimForFab() {

        animator = ObjectAnimator.ofFloat(mFabRefresh, "rotation", 0f, 180f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

    }

    public void playFabAnim() {
        if (!animator.isRunning()) {
            animator.start();
        }

    }

    /**
     * 配置RecyclerView
     */
    public void configRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);    //设置排列方式为垂直
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        adapter = new QueryAdapter(dataList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void turn2LoinActivity() {
        intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showErrorDialog() {
        mBuilder.create().
                show();
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
    public void enableFab() {
        mFabRefresh.setClickable(true);
    }

    @Override
    public void unEnableFab() {
        mFabRefresh.setClickable(false);
    }

    @Override
    public void updateRecyclerView(List<SalaryDetail> dataList) {
        adapter.setList(dataList);
        synchronized (mRecyclerView) {
            mRecyclerView.notify();
        }
    }
}
