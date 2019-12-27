package com.nibalaws.ebrahim.law;

import android.annotation.SuppressLint;
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

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.Tash.Tashahkam;
import com.nibalaws.ebrahim.law.Tash.Tashedit;
import com.nibalaws.ebrahim.law.Tash.Tashlai7a;
import com.nibalaws.ebrahim.law.Tash.Tashmowad;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.lib.colordialog.PromptDialog;


public class tashri3info extends AppCompatActivity {
    ListView list;
    TextView txt;
    TextView txt2;
    public static ArrayList<Master_Stract> listIndex = new ArrayList<>();
    public static ArrayList<Master_Stract> xx = new ArrayList<>();
    TashinfoAdapter adapter2;
    DatabaseHelper db;
    @BindView(R.id.EgyptTash_back_txt)
    TextView EgyptTashBackTxt;
    private SVProgressHUD mSVProgressHUD;
    public static int has_lai7a;
    public static int has_edit;
    public static int has_ahkam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
//        setContentView(R.layout.activity_tashri3info);
        setContentView(R.layout.layout_egyptian_tash);
        ButterKnife.bind(this);
        Util.setViewsTypeface(this, EgyptTashBackTxt);

        db = new DatabaseHelper(this);

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

        TextView titelTxt2 = (TextView) findViewById(R.id.txtTitel);

        db = new DatabaseHelper(this);
        AdapterList adapter = new AdapterList(this, itemname, imgid);
        ListView lstx = findViewById(R.id.list);
        Typeface typex = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        titelTxt2.setTypeface(typex);

        lstx.setTextFilterEnabled(true);
        new SVProgressHUD(this);

        mSVProgressHUD = new SVProgressHUD(this);

        // listIndex = db.gettashinfo("7067");


        adapter2 = new TashinfoAdapter(getApplicationContext(), listIndex);
        list = (ListView) findViewById(R.id.list);
        txt = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt.setText(adapter2.getItem(0).getItem5().toString().replaceAll("(?m)^[ \t]*\r?\n", ""));
        txt2.setText(adapter2.getItem(0).getItem4().toString().replaceAll("(?m)^[ \t]*\r?\n", ""));
        has_lai7a = Integer.parseInt(adapter2.getItem(0).getItem6());
        has_edit = Integer.parseInt(adapter2.getItem(0).getItem7());
        has_ahkam = Integer.parseInt(adapter2.getItem(0).getItem8());

        list.setAdapter(adapter);
        Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        txt.setTypeface(type2);
        Typeface type3 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        txt2.setTypeface(type3);


        lstx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                lstindx = position;
                try {
                    new showTashOptions().execute();


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    String[] itemname = {
            "مواد القانون",
            "اللائحة التنفيذيه",
            "التعديلات المرتبطة",
            "الآحكام المرتبطة",
            "صور التشريع",
    };
    Integer[] imgid = {
            R.drawable.ic_law_articles,
            R.drawable.ic_path_book,
            R.drawable.ic_note,
            R.drawable.ic_law_articles,
            R.drawable.ic_path_copy,
    };


    public static ArrayList<Master_Stract> selectbtn = new ArrayList<>();
    public static int lstindx;
    public static int select_has_val;

    int count_edit = 0;
    Intent intent;

    private class showTashOptions extends AsyncTask<Void, Void, String> {
        @SuppressLint("WrongThread")
        protected String doInBackground(Void... params) {


            if (lstindx == 0) {
                selectbtn = db.Gettashmowad(adapter2.getItem(0).getItem1().toString());

                if (selectbtn.size() > 0) {

                    Tashmowad.Master_Array = selectbtn;
                }


                Tashmowad.titlname = "مواد التشريع";
                Tashmowad.Tashname = txt.getText().toString();


            } else if (lstindx == 1) {

                Tashlai7a.titlname = "اللائحة التنفذية";
                select_has_val = has_lai7a;
                if (select_has_val > 0) {
                    selectbtn = db.Gettashlai7a(adapter2.getItem(0).getItem1().toString());

                    Tashlai7a.Master_Array = selectbtn;
                }


            } else if (lstindx == 2) {
                Tashedit.titlname = "التعديلات المرتبطه";

                select_has_val = has_edit;
                if (select_has_val > 0) {
                    selectbtn = db.Gettashedit(adapter2.getItem(0).getItem1().toString());

                    Tashedit.Master_Array = selectbtn;
                }


            } else if (lstindx == 3) {

                Tashahkam.titlname = "الأحكام المرتبطه";
                select_has_val = has_ahkam;
                if (select_has_val > 0) {
                    selectbtn = db.Gettashahkam(adapter2.getItem(0).getItem1().toString());

                    Tashahkam.Master_Array = selectbtn;
                }


            } else if (lstindx == 4) {
                webview.web_index = "0";
                webview.file = adapter2.getItem(0).getItem3().toString();
                webview.folder = adapter2.getItem(0).getItem2().toString();

            }
            return null;

        }

        @Override

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);

                if (lstindx == 0) {
                    intent = new Intent(tashri3info.this, Tashmowad.class);

                } else if (lstindx == 1) {
                    intent = new Intent(tashri3info.this, Tashlai7a.class);
                    if (select_has_val < 1) {
                        showWarrning("لا تتوافر اللائحة لهذا التشريع");
                        return;
                    }
                } else if (lstindx == 2) {
                    intent = new Intent(tashri3info.this, Tashedit.class);
                    if (select_has_val < 1) {
                        showWarrning("لا تتوافر التعديلات لهذا التشريع");
                        return;
                    }

                } else if (lstindx == 3) {
                    if (select_has_val < 1) {
                        showWarrning("لا تتوافر أحكام لهذا التشريع");
                        return;
                    }

                    intent = new Intent(getApplicationContext(), Tashahkam.class);

                } else if (lstindx == 4) {
                    intent = new Intent(tashri3info.this, webview.class);

                }
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }


    }


    void showWarrning(String msg) {
        new PromptDialog(this)
                .setDialogType(PromptDialog.DIALOG_TYPE_WARNING)
                .setAnimationEnable(true)
                .setTitleText(msg)
                .setContentText("")
                .setPositiveListener(("شكرا"), new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public class AdapterList extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] itemname;
        private final Integer[] imgid;

        public AdapterList(Activity context, String[] itemname, Integer[] imgid) {
            super(context, R.layout.item_tash, itemname);
            // TODO Auto-generated constructor stub

            this.context = context;
            this.itemname = itemname;
            this.imgid = imgid;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.item_tash, null, true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.item1);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.list_image3);

            txtTitle.setText(itemname[position]);
            imageView.setImageResource(imgid[position]);

            Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
            txtTitle.setTypeface(type2);


            return rowView;

        }


    }


    class TashinfoAdapter extends ArrayAdapter<Master_Stract> implements Filterable {


        private class ViewHolder {


        }

        public TashinfoAdapter(Context context, ArrayList<Master_Stract> users) {
            super(context, R.layout.item_tash, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {

            } else {

            }

            return convertView;
        }
    }

    @Override

    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
    }

}