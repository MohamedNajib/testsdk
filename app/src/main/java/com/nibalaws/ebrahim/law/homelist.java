package com.nibalaws.ebrahim.law;

import android.app.Activity;
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
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.balsikandar.crashreporter.CrashReporter;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.Qiod.ListQioad;
import com.nibalaws.ebrahim.law.Qiod.ListQioadpdf;
import com.nibalaws.ebrahim.law.Tash.Tashlist;
import com.nibalaws.ebrahim.law.Tash.Tashlist2;
import com.nibalaws.ebrahim.law.ahkam.ListAhkam;
import com.nibalaws.ebrahim.law.ahkam.Nkd_Master;
import com.nibalaws.ebrahim.law.hitiat.ListHithiat;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class homelist extends AppCompatActivity {
    public static int lstindx;
    public static String titlname;
    @BindView(R.id.EgyptTash_back_txt)
    TextView EgyptTashBackTxt;
    private SVProgressHUD mSVProgressHUD;
    public static int listpo;

    AdapterList adapter;
    DatabaseHelper db;
    Intent intent;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.home_list);
        ButterKnife.bind(this);
        Util.setViewsTypeface(this,EgyptTashBackTxt);
        db = new DatabaseHelper(this);
        mSVProgressHUD = new SVProgressHUD(this);

        materialDesignFAM = findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = findViewById(R.id.material_design_floating_action_menu_item2);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (lstindx == 1) {
                    intent = new Intent(homelist.this, SearchTashActivity.class);
                    intent.putExtra("state", "OfLine");
                    startActivity(intent);
                } else if (lstindx == 0) {
                    Toast.makeText(homelist.this, "0", Toast.LENGTH_SHORT).show();
                    intent = new Intent(homelist.this, SearchAhkamActivity.class);
                    intent.putExtra("state", "OfLine");
                    startActivity(intent);
                } else if (lstindx == 2) {
//                    Toast.makeText(homelist.this, "2", Toast.LENGTH_SHORT).show();
//                    intent = new Intent(homelist.this, searchAll.class);
//                    startActivity(intent);
                    Intent intent = new Intent(homelist.this, SearchHithiatActivity.class);
                    intent.putExtra("state", "OfLine");
                    startActivity(intent);

                } else if (lstindx == 3) {
                    //intent = new Intent(homelist.this, searchAll.class);
                    Intent intent = new Intent(homelist.this, SearchnibaActivity.class);
                    intent.putExtra("state", "OfLine");
                    startActivity(intent);
                }

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "";

                if (lstindx == 0) {
                    Toast.makeText(homelist.this, "0", Toast.LENGTH_SHORT).show();
                    intent = new Intent(homelist.this, SearchAhkamActivity.class);
                    intent.putExtra("state", "OnLine");
                    startActivity(intent);
                   // url = "http://niaba-laws.com/AhkamSerach/";

                } else if (lstindx == 1) {
                    intent = new Intent(homelist.this, SearchTashActivity.class);
                    intent.putExtra("state", "OnLine");
                    startActivity(intent);
                    //url = "https://www.niaba-laws.com/TAShSearch/";

                } else if (lstindx == 2) {
                    Intent intent = new Intent(homelist.this, SearchHithiatActivity.class);
                    intent.putExtra("state", "OnLine");
                    startActivity(intent);
                    //url = "http://niaba-laws.com/SearchHitiat/";

                } else if (lstindx == 3) {
                    Intent intent = new Intent(homelist.this, SearchnibaActivity.class);
                    intent.putExtra("state", "OnLine");
                    startActivity(intent);
                    //url = "http://niaba-laws.com/SearchFehres/";

                }

//                intent = new Intent(getApplicationContext(), webBr.class);
//                webBr.web_url = url;
//                startActivity(intent);

            }
        });


        TextView titelTxt = (TextView) findViewById(R.id.txtTitel);

        Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        titelTxt.setText(titlname);
        titelTxt.setTypeface(type);


        String[] listTash = {"التشريعات الرئيسية", "جميع التشريعات"};
        String[] listnkd = {"النقض المدني", "النقض الجنائي", "الاحكام الدستورية", "القضاء الإداري", "مجلس الدولة", "الإداريا العليا"};
        String[] listqioad = {"قيود واوصاف الجنايات", "قيود واوصاف الجنح", "قيود واوصاف المخالفات", "الأسئلة الإسترشادية", "التعليمات الكتابية", "التعليمات القضائية"};
        String[] listhitiat = {"الحيثيات المدنية", "الحيثيات الجنائية", "المواعيد القانونية", "البطلان الجنائي", "أحكام المحكمة الإقتصادية"};

        if (lstindx == 1) {
            adapter = new AdapterList(this, listTash, null);

        } else if (lstindx == 0) {
            adapter = new AdapterList(this, listnkd, null);

        } else if (lstindx == 3) {
            adapter = new AdapterList(this, listqioad, null);

        } else if (lstindx == 2) {
            adapter = new AdapterList(this, listhitiat, null);
        }

        ListView lstx = (ListView) findViewById(R.id.lstContact);
        lstx.setAdapter(adapter);

        lstx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    listpo = position;
                    if (lstindx == 0) {

                        Screennkd(position);

                    } else if (lstindx == 1) {

                        ScreenTash(position);

                    } else if (lstindx == 2) {

                        Screehitiat(position);

                    } else if (lstindx == 3) {

                        Screeqioad(position);
                    }


                } catch (Exception e) {
                }
            }
        });

        ImageView homeclick = (ImageView) findViewById(R.id.homeclick);
        homeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });

        ImageView backclick = (ImageView) findViewById(R.id.backclik);
        backclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }

    public void ScreenTash(int position) {
        new ScreenTash().execute();
    }

    public void Screennkd(int position) {
        new Screennkd().execute();
    }


    public void Screehitiat(int position) {
        new Screehitiat().execute();
    }

    public void Screeqioad(int position) {
        new Screeqioad().execute();
    }

    // TODO:
    private class Screeqioad extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {

            if (listpo == 0) {
                ListQioad.titlname = "قيود وأوصاف الجنايات";

                ListQioad.Master_Array = db.getQiods("1");

            } else if (listpo == 1) {
                ListQioad.titlname = "قيود وأوصاف الجنح";
                ListQioad.Master_Array = db.getQiods("2");


            } else if (listpo == 2) {
                ListQioad.titlname = "قيود وأوصاف المخالفات";
                ListQioad.Master_Array = db.getQiods("3");


            } else if (listpo == 3) {
                ListQioad.titlname = "الأسئلة الإسترشادية";
                ListQioad.Master_Array = db.getQiods("4");


            } else if (listpo == 5) {
                ListQioad.titlname = "التعليمات القضائية";
                ListQioad.Master_Array = db.getQiods("10");

            } else if (listpo == 4) {
                ListQioad.titlname = "التعليمات الكتابية";
                ListQioad.Master_Array = db.getQiods("9");


            } else if (listpo == 6) {
                ListQioadpdf.titlname = "الكتب الدورية";
                ListQioadpdf.Master_Array = db.pdf_dawry();


            }

            return null;
        }

        Intent intent;

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);


                intent = new Intent(homelist.this, ListQioad.class);

                if (listpo == 6) {
                    String url = "https://www.niaba-laws.com/SearchFehres/Index";


                    intent = new Intent(getApplicationContext(), webBr.class);
                    webBr.web_url = url;
                    startActivity(intent);

                } else if (listpo == 7) {

                }


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                homelist.this.startActivity(intent);


            } catch (Exception e) {
                CrashReporter.logException(e);
            }
        }

        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }


    }

    private class Screehitiat extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {

            if (listpo == 0) {
                ListHithiat.Master_Array = db.Gethitiat("2");
                ListHithiat.titlname = "الحيثيات المدني";

            } else if (listpo == 1) {
                ListHithiat.Master_Array = db.Gethitiat("1");
                ListHithiat.titlname = "الحيثيات الجنائي";

            } else if (listpo == 2) {
                ListQioad.Master_Array = db.getQiods("11");
                ListQioad.titlname = "المواعيد القانونية";
            } else if (listpo == 3) {

                ListQioad.Master_Array = db.getQiods("12");
                ListQioad.titlname = "البطلان الجنائي";

            } else if (listpo == 4) {
                // ListAhkam.Master_Array = db.getAhkam_ektsadia();
                ListAhkam.titlname = "أحكام المحكمة الإقتصادية";


            }
            return null;

        }

        @Override

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);

                if (listpo == 0) {
                    intent = new Intent(homelist.this, ListHithiat.class);

                } else if (listpo == 1) {
                    intent = new Intent(homelist.this, ListHithiat.class);

                } else if (listpo == 2) {
                    intent = new Intent(homelist.this, ListQioad.class);

                } else if (listpo == 3) {

                    intent = new Intent(homelist.this, ListQioad.class);

                } else if (listpo == 4) {
                    intent = new Intent(homelist.this, ListAhkam.class);
                } else if (listpo == 5) {

                    String url = "https://www.niaba-laws.com/SearchHitiat/Index";


                    intent = new Intent(getApplicationContext(), webBr.class);
                    webBr.web_url = url;
                    startActivity(intent);

                }
                startActivity(intent);

            } catch (Exception e) {
                CrashReporter.logException(e);
            }
        }


        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }


    }

    private class Screennkd extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            try {

                if (listpo == 0) {
                    Nkd_Master.ahkam_topic = db.GetahkamTopic("1");
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("1");
                    Nkd_Master.titlname = "النقض المدني";
                } else if (listpo == 1) {
                    Nkd_Master.ahkam_topic = db.GetahkamTopic("2");
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("2");
                    Nkd_Master.titlname = "النقض الجنائي";
                } else if (listpo == 2) {
                    Nkd_Master.ahkam_topic = db.GetahkamTopic("4");
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("4");
                } else if (listpo == 3) {
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("7");
                    Nkd_Master.titlname = "القضاء الإداري";
                } else if (listpo == 4) {
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("8");
                    Nkd_Master.titlname = "مجلس الدولة";
                } else if (listpo == 5) {
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("6");
                    Nkd_Master.titlname = "الإداريا العليا";
                }
            } catch (Exception e) {
                CrashReporter.logException(e);
            }
            return null;

        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            super.onPostExecute(result);
            mSVProgressHUD.dismiss();
            intent = new Intent(homelist.this, Nkd_Master.class);


            if (listpo == 2) {
                Nkd_Master.titlname = "المحكمة الدستورية";


            }

            //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }


    }


    private class ScreenTash extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            try {


                if (listpo == 0) {

                    Tashlist.Master_Array = db.GetimportTash();
                    Tashlist.titlname = "التشريعات الرئيسية";
                } else if (listpo == 1) {

                    Tashlist2.Master_Array = db.gettype();
                    Tashlist2.titlname = "جميع التشريعات";


                }


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


                if (listpo == 0) {
                    intent = new Intent(homelist.this, Tashlist.class);
                } else if (listpo == 1) {
                    intent = new Intent(homelist.this, Tashlist2.class);
                } else if (listpo == 2) {
                    intent = new Intent(homelist.this, SearchTashActivity.class);

                } else if (listpo == 3) {

                    String url = "https://www.niaba-laws.com/TAShSearch/";

                    intent = new Intent(getApplicationContext(), webBr.class);
                    webBr.web_url = url;
                    startActivity(intent);

                }

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                homelist.this.startActivity(intent);
            } catch (Exception e) {
                CrashReporter.logException(e);
            }

        }

        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }


    }

    public class AdapterList extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] itemname;

        public AdapterList(Activity context, String[] itemname, Integer[] imgid) {
            super(context, R.layout.item_, itemname);
            // TODO Auto-generated constructor stub

            this.context = context;
            this.itemname = itemname;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.item_, null, true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.item1);

            txtTitle.setText(itemname[position]);

            Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
            txtTitle.setTypeface(type2);

            return rowView;

        }

    }

    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
    }

    class TashinfoAdapter extends ArrayAdapter<Master_Stract> implements Filterable {


        private class ViewHolder {


        }

        public TashinfoAdapter(Context context, ArrayList<Master_Stract> users) {
            super(context, R.layout.row_item, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {

            } else {

            }

            return convertView;
        }
    }


}

