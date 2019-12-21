package com.nibalaws.ebrahim.law.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import com.balsikandar.crashreporter.CrashReporter;
import com.bigkoo.svprogresshud.SVProgressHUD;

import java.util.ArrayList;
import java.util.List;

import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.HomeActivity;
import com.nibalaws.ebrahim.law.Qiod.textView;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.hitiat.Hitiat_Master;
import com.nibalaws.ebrahim.law.tashri3info;

public class searchAll extends AppCompatActivity implements SearchView.OnQueryTextListener{
    MyAdapterxxx adapter;
    private ListView lv;
    private int indexTap ;
    DatabaseHelper db  ;
    private String searchTxt ="" ;
    private String lstSearch ="" ;
    private TabLayout tabLayout;
    private SearchView sv;
    private SVProgressHUD mSVProgressHUD;
    public static int lstindx;

    public static ArrayList<Master_Stract> Master_Array = new ArrayList<>();


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_frgment);
        SetStyleControls();
        sv = (SearchView) findViewById(R.id.search_view);
        lv = (ListView) findViewById(R.id.lstContact);
        db = new DatabaseHelper(this);
        mSVProgressHUD = new SVProgressHUD(this);
         db =  new DatabaseHelper(this);
        sv.setOnQueryTextListener(this);
         indexTap = 0 ;
        // lv.setAdapter(adapter);


         tabLayout.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 new search().execute() ;
                 return false;

             }
         });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onClick(View view) {




            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (searchTxt.toString().matches("")) {
                  return;
                }
                try {


                    indexTap = tab.getPosition();

                    new search().execute() ;


                } catch (Exception e) {
                    CrashReporter.logException(e);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    lstindx = position ;

                    if (Master_Array.get(position).getItem7().toString().contains("نص") ) {
                              new ViewTxt().execute();

                    }else if   (Master_Array.get(position).getItem7().toString().contains("حيثيات") ) {

                              new ViewHitiat().execute();

                    }else if   (Master_Array.get(position).getItem7().toString().contains("تشريع") ) {

                               new viewtashinfo().execute();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        sv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                Master_Array.clear();
                adapter.notifyDataSetChanged();

                lv.setAdapter(null);



                return false;
            }
        });

        sv.setOnClickListener(  new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sv.setIconified(false);
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
    }



    private class ViewTxt extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            try {
                     textView.ArrayTXt = Master_Array;
                     textView.index =lstindx ;
                     textView.Searchtxt = db.arbicencodicSub(searchTxt.toString()) ;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);
                Intent intent = new Intent(searchAll.this, textView.class);
               startActivity(intent);

            }catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();}

        }
        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }



    }
    void SetStyleControls() {
        TextView titelTxt = (TextView) findViewById(R.id.txtTitel);
        Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        titelTxt.setTypeface(type);
        tabLayout = (TabLayout) findViewById(R.id.tblayoutx);

        TextView tab_0 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab_0.setText("حيثيات");
        Typeface tab0 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        tab_0.setTypeface(tab0);

        TextView tab_1 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab_1.setText("النيابة");
        Typeface tab1 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        tab_1.setTypeface(tab1);

        TextView tab_2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab_2.setText("النقض");
        Typeface tab2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        tab_2.setTypeface(tab2);


        TextView tab_3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab_3.setText("تشريعات");
        Typeface tab3 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        tab_3.setTypeface(tab3);


        tabLayout.getTabAt(0).setCustomView(tab_0);
        tabLayout.getTabAt(1).setCustomView(tab_1);
        tabLayout.getTabAt(2).setCustomView(tab_2);
        tabLayout.getTabAt(3).setCustomView(tab_3);

    }








     @Override
    public boolean onQueryTextSubmit(String s) {
//         Master_Array =  db.searchAllTash(s.toString());
//         layitem = R.layout.row_item_search ;
//        adapter = new MyAdapterxxx(getApplicationContext(), Master_Array);
//        lv.setAdapter(adapter);
         TabLayout.Tab tab = tabLayout.getTabAt(3);
         tab.select();

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        searchTxt = s.toString() ;
        return false;
    }

    public static int layitem = R.layout.row_item ;

    class MyAdapterxxx extends BaseAdapter implements Filterable {
        public  int row_Item_Index = 0 ;
        private Context context;
        public List<Master_Stract> beanList;
        private LayoutInflater inflater;
        List<Master_Stract> mStringFilterList;


        public MyAdapterxxx(Context context, List<Master_Stract> beanList) {
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
                view = inflater.inflate(layitem, null);
            }

            Master_Stract bean = beanList.get(i);
try {
            if (layitem == R.layout.row_item) {
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

            }else if (layitem == R.layout.row_item2){

                TextView item1_txt = (TextView) view.findViewById(R.id.item1);
                String item1 = bean.getItem1();
                item1_txt.setText(item1);
                Typeface type = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-REGULAR.TTF");
                item1_txt.setTypeface(type);


            }else if (layitem == R.layout.row_item_search){


                TextView item1_txt = (TextView) view.findViewById(R.id.item1);
                String item1 = bean.getItem1();
                item1_txt.setText(item1);
                Typeface type = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-REGULAR.TTF");
                item1_txt.setTypeface(type);

                TextView item2_txt = (TextView) view.findViewById(R.id.item2);
                String item2 = bean.getItem3().replaceAll("(?m)^\\s", "");
                item2_txt.setText(item2);
                Typeface type2 = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-MEDIUM.TTF");
                item2_txt.setTypeface(type2);



                TextView item3_txt = (TextView) view.findViewById(R.id.item3);
                String item3 = bean.getItem2().replaceAll("(?m)^\\s", "");
                item3_txt.setText(item3);
                Typeface type3 = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-MEDIUM.TTF");
                item3_txt.setTypeface(type3);


            }

                   }catch (Exception e) {
                      Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                       }






            return view;
        }
        MyAdapterxxx.ValueFilter valueFilter;

        @Override
        public Filter getFilter() {
            if (valueFilter == null) {
                valueFilter = new MyAdapterxxx.ValueFilter();
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




    private class search extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {


            if (indexTap == 0) {
                Master_Array =    db.searchHithiat(searchTxt.toString());

            }else if (indexTap == 1) {

                Master_Array =  db.searchQiods(searchTxt.toString());

            }else if (indexTap == 2) {
                Master_Array =  db.searchNaqdAhkam(searchTxt.toString());


            }else if (indexTap == 3) {
                Master_Array =  db.searchAllTash(searchTxt.toString());

            }
            return null;

        }
        @Override

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);

                if (indexTap == 0) {
                    layitem = R.layout.row_item_search ;
                    adapter = new MyAdapterxxx(getApplicationContext(), Master_Array);
                    lv.setAdapter(adapter);


                } else if (indexTap == 1) {
                    layitem = R.layout.row_item_search ;
                    adapter = new MyAdapterxxx(getApplicationContext(), Master_Array);
                    lv.setAdapter(adapter);

                } else if (indexTap == 2) {
                    layitem = R.layout.row_item_search ;
                    adapter = new MyAdapterxxx(getApplicationContext(), Master_Array);
                    lv.setAdapter(adapter);

                } else if (indexTap == 3) {

                    layitem = R.layout.row_item_search ;
                    adapter = new MyAdapterxxx(getApplicationContext(), Master_Array);
                    lv.setAdapter(adapter);

                }

            }catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }




        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }



    }
Intent intent ;
    private class viewtashinfo extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            try {


                    intent = new Intent(getApplicationContext(), tashri3info.class);
                    tashri3info.listIndex = db.gettashinfo(Master_Array.get(lstindx).getItem4().toString());



            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);
                intent.putExtra("ListtYPE", "تشريعات");
                startActivity(intent);

            }catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();}

        }
        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }



    }
    private class ViewHitiat extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            try {


                intent  = new Intent(getApplicationContext(), Hitiat_Master.class);
                ArrayList<Master_Stract> Hitiatlaw = new ArrayList<>();
                ArrayList<Master_Stract> Hithiatnkd = new ArrayList<>();


                    Hitiatlaw = db.Gethithitlaw(Master_Array.get(lstindx).getItem4(), Master_Array.get(lstindx).getItem2());
                  //  Hithiatnkd = db.Gethithitnkd(Master_Array.get(lstindx).getItem4(), Master_Array.get(lstindx).getItem2());
                    Hitiat_Master.titlname = Master_Array.get(lstindx).getItem1();



                Hitiat_Master.tb_ahkam_years = Hithiatnkd;
                Hitiat_Master.ahkam_topic = Hitiatlaw;



            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
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
    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
    }


}