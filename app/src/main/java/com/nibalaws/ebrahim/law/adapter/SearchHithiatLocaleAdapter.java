package com.nibalaws.ebrahim.law.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchHithiatLocaleAdapter extends RecyclerView.Adapter<SearchHithiatLocaleAdapter.SearchHithiatLocaleHolder> {

    private Context mContext;
    private List<Master_Stract> mSearchHithiat;
    private OnItemClick mOnItemClick;

    public SearchHithiatLocaleAdapter(Context mContext, List<Master_Stract> mSearchHithiat, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.mSearchHithiat = mSearchHithiat;
        this.mOnItemClick = mOnItemClick;
    }

    @Override
    public SearchHithiatLocaleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHithiatLocaleHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_t, parent, false), mOnItemClick);
    }

    @Override
    public void onBindViewHolder(SearchHithiatLocaleHolder holder, int position) {
        Master_Stract search = mSearchHithiat.get(position);
        holder.item1.setText(search.getItem1());
        holder.item2.setText(search.getItem2());
        holder.item3.setText(search.getItem3());

        Util.setViewsTypeface(mContext, holder.item1);
        Util.setViewsTypeface(mContext, holder.item2);
        Util.setViewsTypeface(mContext, holder.item3);
    }

    @Override
    public int getItemCount() {
        return mSearchHithiat.size();
    }

    public class SearchHithiatLocaleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item1)
        TextView item1;
        @BindView(R.id.item2)
        TextView item2;
        @BindView(R.id.item3)
        TextView item3;
        private final View view;

        private OnItemClick mOnItemClick;

        public SearchHithiatLocaleHolder(View itemView, OnItemClick onItemClick) {
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
