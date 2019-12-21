package com.nibalaws.ebrahim.law.DataBaseManger;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.nibalaws.ebrahim.law.R;


/**
 * Created by deepakr on 3/29/2016.
 */
public class MyAdapter extends BaseAdapter implements Filterable {
    public static int row_Item_Index = 0 ;
    private Context context;
    public static List<Master_Stract> beanList;
    private LayoutInflater inflater;
    List<Master_Stract> mStringFilterList;
    ValueFilter valueFilter;


    public MyAdapter(Context context, List<Master_Stract> beanList) {
        this.context = context;
        this.beanList = beanList;
        mStringFilterList = beanList;
    }



   public static int layitem = R.layout.item_m ;


    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int i) {
        return beanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
                  view = inflater.inflate(layitem, null);
        }

        Master_Stract bean = beanList.get(i);

       if (layitem == R.layout.item_m) {
           TextView item1_txt = (TextView) view.findViewById(R.id.item1);
           String item1 = bean.getItem1();
           item1_txt.setText(item1);
           Typeface type = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-REGULAR.TTF");
           item1_txt.setTypeface(type);

           TextView item2_txt = (TextView) view.findViewById(R.id.item2);
           String item2 = bean.getItem2().replaceAll("(?m)^\\s", "");
           item2_txt.setText(item2);
           Typeface type2 = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-MEDIUM.TTF");
           item2_txt.setTypeface(type2);


       }



        return view;
    }



    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Master_Stract> filterList = new ArrayList<Master_Stract>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getItem1().toUpperCase()).contains(constraint.toString().toUpperCase())) {
              Master_Stract bean = new Master_Stract(mStringFilterList.get(i).getItem1(), mStringFilterList.get(i).getItem2()
                      , mStringFilterList.get(i).getItem3()
                      , mStringFilterList.get(i).getItem4()
                      , mStringFilterList.get(i).getItem5()
                      , mStringFilterList.get(i).getItem6()
                      , mStringFilterList.get(i).getItem7()
                      , mStringFilterList.get(i).getItem8());
                        filterList.add(bean);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            beanList = (ArrayList<Master_Stract>) results.values;
            notifyDataSetChanged();
        }

    }



}

