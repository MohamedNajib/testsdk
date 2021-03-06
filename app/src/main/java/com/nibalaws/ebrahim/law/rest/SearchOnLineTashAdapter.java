package com.nibalaws.ebrahim.law.rest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchOnLineTashAdapter extends RecyclerView.Adapter<SearchOnLineTashAdapter.SearchTashHolder> {


    private List<Master_Stract> master_stracts;


    public SearchOnLineTashAdapter(List<Master_Stract> master_stracts) {
        this.master_stracts = master_stracts;
    }

    @Override
    public SearchTashHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchTashHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_tsh, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchTashHolder holder, int position) {
        Master_Stract masterStract = master_stracts.get(position);
        holder.infoText.setText(masterStract.getItem2());
    }

    @Override
    public int getItemCount() {
        return master_stracts.size();
    }

    public class SearchTashHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.infoText)
        TextView infoText;
        private final View view;
        public SearchTashHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
