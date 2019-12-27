package com.nibalaws.ebrahim.law.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchResponse;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchNiabhApiAdapter extends RecyclerView.Adapter<SearchNiabhApiAdapter.SearchHithiatApiHolder> {



    private Context mContext;
    private List<SearchResponse> searchesList;
    private OnItemClick mOnItemClick;

    public SearchNiabhApiAdapter(Context mContext, List<SearchResponse> searchesList, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.searchesList = searchesList;
        this.mOnItemClick = mOnItemClick;
    }

    @Override
    public SearchHithiatApiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHithiatApiHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_m, parent, false), mOnItemClick);
    }

    @Override
    public void onBindViewHolder(SearchHithiatApiHolder holder, int position) {
        SearchResponse search = searchesList.get(position);
        holder.item1.setText(search.getInfo());
        holder.item2.setText(search.getDetails());

        Util.setViewsTypeface(mContext, holder.item1);
        Util.setViewsTypeface(mContext, holder.item2);

    }

    @Override
    public int getItemCount() {
        return searchesList.size();
    }

    public class SearchHithiatApiHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item1)
        TextView item1;
        @BindView(R.id.item2)
        TextView item2;
        private final View view;
        private OnItemClick mOnItemClick;

        public SearchHithiatApiHolder(View itemView, OnItemClick onItemClick) {
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
