package com.nibalaws.ebrahim.law.GridCustome;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.R;

/**
 * Created by ebrahim on 12/15/17.
 */

public class CustomGridViewActivity extends BaseAdapter {

    private Context mContext;
    private final String[] gridViewString;
    private final int[] gridViewImageId;

    public CustomGridViewActivity(Context context, String[] gridViewString, int[] gridViewImageId) {
        mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
    }

    @Override
    public int getCount() {
        return gridViewString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            String vv = gridViewString[i] ;
            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.row_grid, null);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.item_text);
            ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.item_image);
            textViewAndroid.setText(gridViewString[i]);
            Typeface type = Typeface.createFromAsset(mContext.getAssets(),"NG4ASANS-REGULAR.TTF");
            textViewAndroid.setTypeface(type);

            imageViewAndroid.setImageResource(gridViewImageId[i]);
        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }


}
