package com.nibalaws.ebrahim.law.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchAhkamResponse;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchApiAhkamAdapter extends RecyclerView.Adapter<SearchApiAhkamAdapter.SearchApiAhkamHolder> {

    private Context mContext;
    private List<SearchAhkamResponse> mSearchAhkamList;
    private OnItemClick mOnItemClick;

    public SearchApiAhkamAdapter(Context mContext, List<SearchAhkamResponse> mSearchAhkamList, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.mSearchAhkamList = mSearchAhkamList;
        this.mOnItemClick = mOnItemClick;
    }

    @Override
    public SearchApiAhkamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchApiAhkamHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_n, parent, false), mOnItemClick);
    }

    @Override
    public void onBindViewHolder(SearchApiAhkamHolder holder, int position) {
        SearchAhkamResponse searchAhkamResponse = mSearchAhkamList.get(position);
        holder.item1.setText(searchAhkamResponse.getInfo());
        holder.item2.setText(searchAhkamResponse.getDetails());

        Util.setViewsTypeface(mContext, holder.item1);
        Util.setViewsTypeface(mContext, holder.item2);
    }

    @Override
    public int getItemCount() {
        return mSearchAhkamList.size();
    }

    public class SearchApiAhkamHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item1)
        TextView item1;
        @BindView(R.id.item2)
        TextView item2;

        private final View view;
        private OnItemClick mOnItemClick;

        public SearchApiAhkamHolder(View itemView, OnItemClick onItemClick) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnItemClick = onItemClick;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                mOnItemClick.setOnItemClicked(position);
            }
        }
    }

    public interface OnItemClick {
        void setOnItemClicked(int position);
    }
}
