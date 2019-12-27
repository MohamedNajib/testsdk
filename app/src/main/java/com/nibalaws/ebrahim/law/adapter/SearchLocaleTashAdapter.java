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

public class SearchLocaleTashAdapter extends RecyclerView.Adapter<SearchLocaleTashAdapter.SearchTashHolder> {

    private Context mContext;
    private List<Master_Stract> master_stracts;
    private String searchText;
    private OnItemClick mOnItemClick;

    public SearchLocaleTashAdapter(Context mContext, List<Master_Stract> master_stracts, String searchText, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.master_stracts = master_stracts;
        this.searchText = searchText;
        this.mOnItemClick = mOnItemClick;
    }

    @Override
    public SearchTashHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchTashHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_, parent, false), mOnItemClick);
    }

    @Override
    public void onBindViewHolder(SearchTashHolder holder, int position) {
        Master_Stract masterStract = master_stracts.get(position);
        holder.infoText.setText(masterStract.getItem2());

        Util.setViewsTypeface(mContext, holder.infoText);
        Util.SelectionSearchWord(searchText, searchText,  holder.infoText);
    }

    @Override
    public int getItemCount() {
        return master_stracts.size();
    }

    public class SearchTashHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item1)
        TextView infoText;

        private OnItemClick mOnItemClick;
        private final View view;

        public SearchTashHolder(View itemView, OnItemClick onItemClick) {
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
