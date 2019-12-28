package com.nibalaws.ebrahim.law.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchTashResponse;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchApiTashAdapter extends RecyclerView.Adapter<SearchApiTashAdapter.SearchApiTashHolder>
        implements Filterable {

    private Context mContext;
    private List<SearchTashResponse> mSearchTashList;
    private List<SearchTashResponse> mSearchTashListFull;
    private OnItemClick mOnItemClick;

    public SearchApiTashAdapter(Context mContext, List<SearchTashResponse> searchTash, OnItemClick mOnItemClick) {
        this.mContext = mContext;
        this.mSearchTashList = searchTash;
        this.mOnItemClick = mOnItemClick;
        mSearchTashListFull = new ArrayList<>(searchTash);
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<SearchTashResponse> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(mSearchTashListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (SearchTashResponse item : mSearchTashListFull) {
                    if (item.getInfo().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mSearchTashList.clear();
            mSearchTashList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
