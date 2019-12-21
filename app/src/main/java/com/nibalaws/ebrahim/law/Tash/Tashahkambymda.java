package com.nibalaws.ebrahim.law.Tash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;

import java.util.ArrayList;

import com.nibalaws.ebrahim.law.HomeActivity;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.ahkam.textViewahkam;


public class Tashahkambymda extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static int lstindx;
    public static String titlname;
    private SVProgressHUD mSVProgressHUD;
    int progress = 0;
    private ListView lv;
    private SearchView sv;
    TextView titelTxt;

    CustomAdapter adapter;
    public static ArrayList<Master_Stract> Master_Array = new ArrayList<>();
    public static ArrayList<Master_Stract> fillterArray = new ArrayList<>();
    private Boolean ISsEARCHING = false;

    DatabaseHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.itemlist);
        setContentView(R.layout.layout_favorite);
        sv = (SearchView) findViewById(R.id.search_view);
        lv = (ListView) findViewById(R.id.lstContact);
        mSVProgressHUD = new SVProgressHUD(this);
        db =  new DatabaseHelper(this);
        titelTxt = (TextView) findViewById(R.id.txtTitel);
        Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        titelTxt.setText(titlname);
        titelTxt.setTypeface(type);
        adapter = new  CustomAdapter(getApplicationContext(), Master_Array);
        sv.setOnQueryTextListener(this);
        lv.setAdapter(adapter);
        sv.setOnQueryTextListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {

                    lstindx = position ;
                new viewtxt().execute() ;

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        ImageView homeclick= (ImageView) findViewById(R.id.homeclick);
        homeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });
        ImageView backclick= (ImageView) findViewById(R.id.backclik);
        backclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        sv.setOnClickListener(  new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sv.setIconified(false);
            }
        });

    }

    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
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
                valueFilter = new  CustomAdapter.ValueFilter();
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




    Intent intent;

    private class viewtxt extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {

            intent = new Intent(getApplicationContext(), textViewahkam.class);

            if (ISsEARCHING) {
                textViewahkam.ArrayTXt = fillterArray;
            }else{
                textViewahkam.ArrayTXt = Master_Array;
            }
            textViewahkam.index = lstindx;



            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);
                 startActivity(intent);

            }catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();}

        }
        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }



    }

}

