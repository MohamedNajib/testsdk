package com.nibalaws.ebrahim.law.hitiat;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.HomeActivity;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.ahkam.Nkd_Master;
import com.nibalaws.ebrahim.law.hitiat.fregment2.fregment_one_H;
import com.nibalaws.ebrahim.law.hitiat.fregment2.fregment_two_H;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Hitiat_Master extends AppCompatActivity {
    public static String titlname;
    public static ArrayList<Master_Stract> ahkam_topic = new ArrayList<>();
    public static ArrayList<Master_Stract> tb_ahkam_years = new ArrayList<>();
    DatabaseHelper db;

    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.Home_Fragment_ViewPager)
    ViewPager viewPager;
    @BindView(R.id.EgyptTash_back_txt)
    TextView EgyptTashBackTxt;

    private Toolbar toolbar;
    //private TabLayout tabLayout;
    private RelativeLayout relative;
    private RelativeLayout relative2;

    private GradientDrawable drawable1;
    private GradientDrawable drawable2;

    private int[] tabIcons = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,

    };

    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
    }

    public Hitiat_Master() {
    }

    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        //setContentView(R.layout.master_frgment);
        setContentView(R.layout.layout_civil_law);
        ButterKnife.bind(this);
        Util.setViewsTypeface(this, EgyptTashBackTxt);
        db = new DatabaseHelper(this);
        drawable1 = (GradientDrawable) button3.getBackground();
        drawable2 = (GradientDrawable) button2.getBackground();
        TextView titelTxt = (TextView) findViewById(R.id.txtTitel);
        Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        titelTxt.setText(titlname);
        titelTxt.setTypeface(type);

        Util.setViewsTypeface(this, button2);
        Util.setViewsTypeface(this, button3);


        //viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        relative = (RelativeLayout) findViewById(R.id.relative);
        relative2 = (RelativeLayout) findViewById(R.id.relative2);

        //////
        drawable1.setStroke(2, getResources().getColor(R.color.a));
        drawable2.setStroke(2, getResources().getColor(R.color.a));

        drawable1.setColor(getResources().getColor(R.color.a));
        drawable2.setColor(getResources().getColor(R.color.b));

        button3.setTextColor(getResources().getColor(R.color.white));
        button2.setTextColor(getResources().getColor(R.color.t));
        viewPager.setCurrentItem(1, true);

        //////

//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);

//            tabLayout.setVisibility(View.GONE);
//            relative2.setVisibility(View.GONE);
//            relative.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;
//            setMargins(relative,0,0,0,0);
        //setupTabIcons();

        fregment_one_H.listyear = tb_ahkam_years;
        fregment_two_H.listtopics = ahkam_topic;
        fregment_one_H.ListtYPE = "حيثايات";
        fregment_two_H.ListtYPE = "حيثايات";

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

        button2.setText("أحكام");
        button3.setText("قوانين");

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    drawable1.setStroke(2, getResources().getColor(R.color.a));
                    drawable2.setStroke(2, getResources().getColor(R.color.a));

                    drawable1.setColor(getResources().getColor(R.color.b));
                    drawable2.setColor(getResources().getColor(R.color.a));

                    button2.setTextColor(getResources().getColor(R.color.white));
                    button3.setTextColor(getResources().getColor(R.color.t));
                }else if (position == 1){
                    drawable1.setStroke(2, getResources().getColor(R.color.a));
                    drawable2.setStroke(2, getResources().getColor(R.color.a));

                    drawable1.setColor(getResources().getColor(R.color.a));
                    drawable2.setColor(getResources().getColor(R.color.b));

                    button3.setTextColor(getResources().getColor(R.color.white));
                    button2.setTextColor(getResources().getColor(R.color.t));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    private void setupTabIcons() {
//
//
//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("أحكام");
//        Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
//        tabOne.setTypeface(type2);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
//
//
//        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabTwo.setText("قوانين");
//        Typeface type3 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
//        tabTwo.setTypeface(type3);
//        tabLayout.getTabAt(1).setCustomView(tabTwo);
//    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new fregment_one_H());
// , "أحكام", "قوانين"
        adapter.addFrag(new fregment_two_H());

        viewPager.setAdapter(adapter);
    }

    @OnClick({R.id.button3, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button3:
                viewPager.setCurrentItem(1, true);

                drawable1.setStroke(2, getResources().getColor(R.color.a));
                drawable2.setStroke(2, getResources().getColor(R.color.a));

                drawable1.setColor(getResources().getColor(R.color.a));
                drawable2.setColor(getResources().getColor(R.color.b));
                button3.setTextColor(getResources().getColor(R.color.white));
                button2.setTextColor(getResources().getColor(R.color.t));

                break;
            case R.id.button2:
                viewPager.setCurrentItem(0, true);
                drawable1.setStroke(2, getResources().getColor(R.color.a));
                drawable2.setStroke(2, getResources().getColor(R.color.a));

                drawable1.setColor(getResources().getColor(R.color.b));
                drawable2.setColor(getResources().getColor(R.color.a));

                button2.setTextColor(getResources().getColor(R.color.white));
                button3.setTextColor(getResources().getColor(R.color.t));
                break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }
}