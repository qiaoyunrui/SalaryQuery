package com.juhezi.salaryquery.query;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.juhezi.salaryquery.R;
import com.juhezi.salaryquery.data.SalaryDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaoyunrui on 16-7-21.
 */
public class QueryAdapter extends BaseAdapter {

    public List<SalaryDetail> salaryList = new ArrayList<>();   //存放工资条目的线性表
    private TextView mTvTitle;
    private TextView mTvContent;

    @Override
    public int getCount() {
        return salaryList.size();
    }

    @Override
    public SalaryDetail getItem(int i) {
        return salaryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = view;
        if (null == rootView) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            rootView = inflater.inflate(R.layout.query_list_item, viewGroup, false);
        }
        mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        mTvContent = (TextView) rootView.findViewById(R.id.tv_content);
        final SalaryDetail detail = getItem(i);
        mTvTitle.setText(detail.getTitle());
        mTvContent.setText(detail.getTitle());
        return rootView;
    }

    public List<SalaryDetail> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(List<SalaryDetail> salaryList) {
        this.salaryList = salaryList;
    }

    public QueryAdapter(List<SalaryDetail> salaryList) {
        this.salaryList = salaryList;
    }

}
