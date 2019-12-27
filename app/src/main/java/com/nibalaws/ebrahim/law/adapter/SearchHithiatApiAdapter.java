package com.nibalaws.ebrahim.law.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.rest.apiModel.Search;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchResponse;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchHithiatApiAdapter extends RecyclerView.Adapter<SearchHithiatApiAdapter.SearchHithiatApiHolder> {



    private Context mContext;
    private List<Search> searchesList;
    private OnItemClick mOnItemClick;

    public SearchHithiatApiAdapter(Context mContext, List<Search> searchesList, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.searchesList = searchesList;
        this.mOnItemClick = mOnItemClick;
    }

    @Override
    public SearchHithiatApiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHithiatApiHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_, parent, false), mOnItemClick);
    }

    @Override
    public void onBindViewHolder(SearchHithiatApiHolder holder, int position) {
        Search search = searchesList.get(position);
        holder.item1.setText(search.getInfo());
        Util.setViewsTypeface(mContext, holder.item1);
    }

    @Override
    public int getItemCount() {
        return searchesList.size();
    }

    public class SearchHithiatApiHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item1)
        TextView item1;

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
