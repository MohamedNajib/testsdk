package com.nibalaws.ebrahim.law.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchTashResponse;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchApiTashAdapter extends RecyclerView.Adapter<SearchApiTashAdapter.SearchApiTashHolder> {

    private Context mContext;
    private List<SearchTashResponse> mSearchTashList;
    private OnItemClick mOnItemClick;

    public SearchApiTashAdapter(Context mContext, List<SearchTashResponse> searchTash, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.mSearchTashList = searchTash;
        this.mOnItemClick = mOnItemClick;
    }

    @Override
    public SearchApiTashHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchApiTashHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_, parent, false), mOnItemClick);
    }

    @Override
    public void onBindViewHolder(SearchApiTashHolder holder, int position) {
        SearchTashResponse searchTash = mSearchTashList.get(position);
        holder.infoText.setText(searchTash.getInfo());

        Util.setViewsTypeface(mContext, holder.infoText);
    }

    @Override
    public int getItemCount() {
        return mSearchTashList.size();
    }

    public class SearchApiTashHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item1)
        TextView infoText;

        private OnItemClick mOnItemClick;
        private final View view;

        public SearchApiTashHolder(View itemView, OnItemClick onItemClick) {
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
