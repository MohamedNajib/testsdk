package com.nibalaws.ebrahim.law;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.balsikandar.crashreporter.CrashReporter;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Var;
import com.nibalaws.ebrahim.law.GridCustome.CustomGridViewActivity;
import com.nibalaws.ebrahim.law.search.searchAll;

import static com.balsikandar.crashreporter.CrashReporter.getContext;


public class HomaPage extends AppCompatActivity {
    DatabaseHelper db ;

    GridView androidGridView;

    String[] gridViewString = {
            "احكام النقض", "التشريعات", "العمليه", "موسوعة النيابة", "البحث العام", "المفضلة","اتصل بنا", "الاعدادات",


    } ;
    int[] gridViewImageId = {
            R.drawable.naqdhome3x, R.drawable.tashreathome3x, R.drawable.haithiathome3x, R.drawable.qiodhome3x, R.drawable.searchhome3x, R.drawable.bookmarkshome3x,
            R.drawable.contacthome3x, R.drawable.settingshome3x
    };

    TextView bt ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        db  = new DatabaseHelper(this);
        //SetActive();
        try{
            setContentView(R.layout.activity_grid_home);

            setContentView(R.layout.activity_grid_home);

        } catch (Exception e) {
            CrashReporter.logException(e);
        }
        db =  new DatabaseHelper(this);
        bt = (TextView) findViewById(R.id.bt_active);

        bt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                Intent intent = new Intent( getApplicationContext(), Tanshet.class);
                startActivity(intent);
            }
        });

        TextView titelTxt = (TextView) findViewById(R.id.txtname);
        Typeface type = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        titelTxt.setTypeface(type);


        TextView bt_active =  (TextView) findViewById(R.id.bt_active);
        Typeface typex = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        bt_active.setTypeface(typex);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(HomaPage.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.grid_item_anim);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
        //  androidGridView.setLayoutAnimation(controller);

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String tt = "";
                try {

                    Intent intent = new Intent( getApplicationContext(), homelist.class);
                    homelist.lstindx = i ;

                    if (i == 0) {
                        tt ="احكام النقض" ;
                        homelist.titlname  = tt ;
                        startActivity(intent);
                    }else if (i == 1) {
                        tt ="التشريعات" ;
                        homelist.titlname  = tt ;
                        startActivity(intent);
                    }else if (i == 2) {
                        tt ="العمليه" ;
                        homelist.titlname  = tt ;
                        startActivity(intent);
                    }else if (i == 3) {
                        tt ="القيود والاوصاف" ;
                        homelist.titlname  = tt ;
                        startActivity(intent);
                    }else if (i == 4) {
                        intent = new Intent( getApplicationContext(), searchAll.class);
                        startActivity(intent);
                    }else if (i == 5) {
                        tt ="المفضلة" ;
                        intent = new Intent(getApplicationContext(), ListFav.class);
                        //ListFav.titlname = "المفضلة";
                        ListFav.Master_Array = db.ShowFav();
                        startActivity(intent);
                    }else if (i == 6) {
                        tt ="اتصل بنا" ;
                        intent = new Intent(getApplicationContext(), contactus.class);
                        startActivity(intent);
                    }else if (i == 7) {
                        tt ="الاعدادات" ;
                        intent = new Intent(getApplicationContext(), settings.class);
                        startActivity(intent);
                    }



                } catch (Exception e) {
                    CrashReporter.logException(e);
                }




            }
        });
        call_Setting();
    }


    void SetActive() {
        Var.Active = true ;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("Act", true);
        editor.apply();


    }
    void call_Setting(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        Var.FontSizeSettings = pref.getInt("fontsize", 12) ;
        Var.SoundStart = (pref.getBoolean("sound", false));
        Var.Active = (pref.getBoolean("Act", false));

        if (Var.Active == true) {
            bt.setVisibility(View.INVISIBLE);
        }else{
            bt.setVisibility(View.VISIBLE);


        }

    }

}
