package com.nibalaws.ebrahim.law.hitiat.fregment2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;

import java.util.ArrayList;

import com.nibalaws.ebrahim.law.Qiod.textView;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.hitiat.textViewHithiat;

public class fregment_one_H extends Fragment implements SearchView.OnQueryTextListener {
    TextView txt ;
    public static ArrayList<Master_Stract> listyear = new ArrayList<>();
    public static ArrayList<Master_Stract> fillterArray = new ArrayList<>();
    public static int lstindx;

    private SearchView sv;
    DatabaseHelper db ;
    private Boolean ISsEARCHING = false;
    CustomAdapter adapter1 ;
    public static String ListtYPE = "" ;
    private SVProgressHUD mSVProgressHUD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fregmentlist, container, false);

    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lstx = (ListView) view.findViewById(R.id.lstContact);
        db = new DatabaseHelper(getContext()) ;
         adapter1 = new CustomAdapter(getContext(),listyear);
         lstx.setAdapter(adapter1);
        lstx.setTextFilterEnabled(true);
        sv = (SearchView) view.findViewById(R.id.search_view);
        mSVProgressHUD = new SVProgressHUD(getContext());

        sv.setOnQueryTextListener(this);

        lstx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lstindx = position ;
                new ViewTxt().execute() ;
          }
        });
        sv.setOnClickListener(  new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sv.setIconified(false);
            }
        });
    }




    void txtview(int po) {
       int global_position = 0; // your global variable
        global_position = po;
        String aString = Integer.toString(global_position);
        Intent intent = new Intent(getContext(), textView.class);
        textView.ArrayTXt = listyear;
        textView.index = po;
        startActivity(intent);

    }
         @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter1.getFilter().filter(s);
        return false;
    }

    public static ArrayList<Master_Stract> mStringFilterList = new ArrayList<>();

    class CustomAdapter extends BaseAdapter implements Filterable {
        public int row_Item_Index = 0;
        private Context context;
        public ArrayList<Master_Stract> beanList;
        private LayoutInflater inflater;


        public CustomAdapter(Context context, ArrayList<Master_Stract> beanList) {
            this.context = context;
            this.beanList = beanList;
            mStringFilterList = beanList;
        }

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
                view = inflater.inflate(R.layout.item_m, null);
            }

            Master_Stract bean = beanList.get(i);

            TextView item1_txt = (TextView) view.findViewById(R.id.item1);
            String item1 = bean.getItem1();
            item1_txt.setText(item1);
            Typeface type = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-REGULAR.TTF");
            item1_txt.setTypeface(type);

            TextView item2_txt = (TextView) view.findViewById(R.id.item2);
            String item2 = bean.getItem2().replaceAll("(?m)^[ \t]*\r?\n", "");
            item2_txt.setText(item2);
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-MEDIUM.TTF");
            item2_txt.setTypeface(type2);

            return view;
        }


        CustomAdapter.ValueFilter valueFilter;

        @Override
        public Filter getFilter() {
            if (valueFilter == null) {
                valueFilter = new   CustomAdapter.ValueFilter();
            }
            return valueFilter;
        }


        private class ValueFilter extends Filter {
             @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String Ser = constraint.toString();
                if (db.Converten(constraint.toString()) != "") {
                    Ser = db.Converten(constraint.toString());
                }
                if (Ser != null && Ser.length() > 0) {
                    ArrayList<Master_Stract> filterList = new ArrayList<Master_Stract>();
                    for (int i = 0; i < mStringFilterList.size(); i++) {
                        if ((mStringFilterList.get(i).getItem1().toLowerCase()).contains(Ser.toString().toLowerCase())) {
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
                    fillterArray = filterList;
                    ISsEARCHING = true;
                } else {
                    results.count = mStringFilterList.size();
                    results.values = mStringFilterList;
                    ISsEARCHING = false;

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




    private class ViewTxt extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            try {


                if (ISsEARCHING) {
                    textViewHithiat.ArrayTXt = fillterArray;
                }else{
                    textViewHithiat.ArrayTXt = listyear;
                }

            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;


        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);
                Intent intent = new Intent(getContext(), textViewHithiat.class);
                textViewHithiat.index = lstindx;
                startActivity(intent);

            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }



    }




}