package com.nibalaws.ebrahim.law.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.rest.DialogSearchDataModel;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;

public class DialogSearchCustomAdapter extends ArrayAdapter<DialogSearchDataModel> {

    private ArrayList<DialogSearchDataModel> originalList;
    private ArrayList<DialogSearchDataModel> modelList;

    private TypeFilter filter;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        CheckBox checkBox;
        RelativeLayout RelativeLayoutItem;
    }

    public DialogSearchCustomAdapter(ArrayList<DialogSearchDataModel> data, int resourceId, Context context) {
        super(context, resourceId, data);

        this.modelList = new ArrayList<DialogSearchDataModel>();
        this.modelList.addAll(data);
        this.originalList = new ArrayList<DialogSearchDataModel>();
        this.originalList.addAll(data);
        this.mContext = context;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new TypeFilter();
        }
        return filter;
    }

//    @Override
//    public int getCount() {
//        return originalList.size();
//    }

//    @Nullable
//    @Override
//    public DialogSearchDataModel getItem(int position) {
////        return super.getItem(position);
//        return originalList.get(position);
//    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_row_item, parent, false);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            viewHolder.RelativeLayoutItem = convertView.findViewById(R.id.RelativeLayoutItem);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        DialogSearchDataModel item = getItem(position);


        viewHolder.txtName.setText(item.name);
        viewHolder.checkBox.setChecked(item.checked);

        Util.setViewsTypeface(mContext, viewHolder.txtName);

        if (viewHolder.checkBox.isChecked()) {
            viewHolder.RelativeLayoutItem.setBackgroundColor(mContext.getResources().getColor(R.color.itemBackground));
        } else {
            viewHolder.RelativeLayoutItem.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }


        return result;
    }

    public class TypeFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            charSequence = charSequence.toString().toLowerCase();
            FilterResults result = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {

                ArrayList<DialogSearchDataModel> filteredItems = new ArrayList<>();
                for (int i = 0, l = originalList.size(); i < l; i++) {
                    DialogSearchDataModel dataModel = originalList.get(i);
                    if (dataModel.toString().toLowerCase().contains(charSequence))
                        filteredItems.add(dataModel);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            } else {
                synchronized (this) {
                    result.values = originalList;
                    result.count = originalList.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            modelList = (ArrayList<DialogSearchDataModel>) filterResults.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = modelList.size(); i < l; i++)
                add(modelList.get(i));
            notifyDataSetInvalidated();
        }
    }
}
