package com.nibalaws.ebrahim.law.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchAllResponse;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAllAdapter extends RecyclerView.Adapter<SearchAllAdapter.SearchAllHolder> {

    private List<SearchAllResponse> mSearchAllList;
    private Context mContext;
    private OnItemClick mOnItemClick;

    public SearchAllAdapter(List<SearchAllResponse> mSearchAllList, Context mContext, OnItemClick onItemClick) {
        this.mSearchAllList = mSearchAllList;
        this.mContext = mContext;
        this.mOnItemClick = onItemClick;
    }

    @Override
    public SearchAllHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchAllHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_t, parent, false), mOnItemClick);
    }

    @Override
    public void onBindViewHolder(SearchAllHolder holder, int position) {
        SearchAllResponse searchAllResponse = mSearchAllList.get(position);
        holder.item1.setText(searchAllResponse.getSubInfo());
        holder.item2.setText(searchAllResponse.getInfo());
        holder.item3.setText(searchAllResponse.getData());

        holder.item1.setTextColor(mContext.getResources().getColor(R.color.myPrimaryColor));
        holder.item2.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        holder.item3.setTextColor(mContext.getResources().getColor(R.color.colorAccent));

        Util.setViewsTypeface(mContext, holder.item1);
        Util.setViewsTypeface(mContext, holder.item2);
        Util.setViewsTypeface(mContext, holder.item3);
    }

    @Override
    public int getItemCount() {
        return mSearchAllList.size();
    }

    public class SearchAllHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item1)
        TextView item1;
        @BindView(R.id.item2)
        TextView item2;
        @BindView(R.id.item3)
        TextView item3;
        private final View view;

        private OnItemClick mOnItemClick;

        public SearchAllHolder(View itemView, OnItemClick onItemClick) {
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
