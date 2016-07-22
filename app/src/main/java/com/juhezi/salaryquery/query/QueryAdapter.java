package com.juhezi.salaryquery.query;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juhezi.salaryquery.R;
import com.juhezi.salaryquery.data.SalaryDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaoyunrui on 16-7-22.
 */
public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder> {

    private List<SalaryDetail> list = new ArrayList<>();

    public QueryAdapter(List<SalaryDetail> list) {
        this.list = list;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.query_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //将数据与界面进行绑定
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (null != list) {
            holder.mTvTitle.setText(list.get(position).getTitle());
            holder.mTvContent.setText(list.get(position).getContent());
        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<SalaryDetail> getList() {
        return list;
    }

    public void setList(List<SalaryDetail> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTvTitle;
        public TextView mTvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

}
