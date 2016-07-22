package com.juhezi.salaryquery.query;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.juhezi.salaryquery.R;
import com.juhezi.salaryquery.data.SalaryDetail;
import com.juhezi.salaryquery.login.LoginContract;

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

    private QueryContract.Presenter mPresenter;

    private ObjectAnimator animator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.query_frag, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);
        mFabRefresh = (FloatingActionButton) rootView.findViewById(R.id.fab_refresh);
        configRecyclerView();
        initEvent();
        setAnimForFab();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
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
        List<SalaryDetail> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new SalaryDetail("姓名" + i, "张全蛋" + i));
        }
        QueryAdapter adapter = new QueryAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }
}
