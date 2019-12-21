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

public class SearchLocaleAhkamAdapter extends RecyclerView.Adapter<SearchLocaleAhkamAdapter.SearchLocaleAhkamHolder> {

    private Context mContext;
    private List<Master_Stract> mMasterStracts;
    private OnItemClick mOnItemClick;

    public SearchLocaleAhkamAdapter(Context mContext, List<Master_Stract> masterStracts, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.mMasterStracts = masterStracts;
        this.mOnItemClick = mOnItemClick;
    }

    @Override
    public SearchLocaleAhkamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchLocaleAhkamHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_t, parent, false), mOnItemClick);
    }

    @Override
    public void onBindViewHolder(SearchLocaleAhkamHolder holder, int position) {
        Master_Stract masterStract = mMasterStracts.get(position);
        holder.item1.setText(masterStract.getItem1());
        holder.item2.setText(masterStract.getItem2());
        holder.item3.setText(masterStract.getItem6());

        Util.setViewsTypeface(mContext, holder.item1);
        Util.setViewsTypeface(mContext, holder.item2);
        Util.setViewsTypeface(mContext, holder.item3);
    }

    @Override
    public int getItemCount() {
        return mMasterStracts.size();
    }

    public class SearchLocaleAhkamHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item1)
        TextView item1;
        @BindView(R.id.item2)
        TextView item2;
        @BindView(R.id.item3)
        TextView item3;

        private final View view;
        private OnItemClick mOnItemClick;

        public SearchLocaleAhkamHolder(View itemView, OnItemClick onItemClick) {
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
