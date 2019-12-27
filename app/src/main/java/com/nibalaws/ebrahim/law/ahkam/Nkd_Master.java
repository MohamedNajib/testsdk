package com.nibalaws.ebrahim.law.ahkam;

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
import android.widget.TextView;
import android.widget.Toast;

import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.DataBaseManger.MyAdapter;
import com.nibalaws.ebrahim.law.HomeActivity;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.ahkam.fregment.fregment_one;
import com.nibalaws.ebrahim.law.ahkam.fregment.fregment_two;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Nkd_Master extends AppCompatActivity {
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

    private GradientDrawable drawable1;
    private GradientDrawable drawable2;

    private Toolbar toolbar;
    //    private TabLayout tabLayout;

    //    private ViewPager viewPager;
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

    public Nkd_Master() {
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
//        setContentView(R.layout.master_frgment);
        Util.setLocaleAr(this);
        setContentView(R.layout.layout_civil_law);
        ButterKnife.bind(this);
        Util.setViewsTypeface(this, EgyptTashBackTxt);
        db = new DatabaseHelper(this);
        MyAdapter.layitem = R.layout.row_item;

        drawable1 = (GradientDrawable) button3.getBackground();
        drawable2 = (GradientDrawable) button2.getBackground();


        Util.setViewsTypeface(this, button2);
        Util.setViewsTypeface(this, button3);

        TextView titelTxt = (TextView) findViewById(R.id.txtTitel);
        Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        titelTxt.setText(titlname);
        titelTxt.setTypeface(type);

        setupViewPager(viewPager);

        /////
        drawable1.setStroke(2, getResources().getColor(R.color.a));
        drawable2.setStroke(2, getResources().getColor(R.color.a));

        drawable1.setColor(getResources().getColor(R.color.a));
        drawable2.setColor(getResources().getColor(R.color.b));

        button3.setTextColor(getResources().getColor(R.color.white));
        button2.setTextColor(getResources().getColor(R.color.t));
        viewPager.setCurrentItem(1, true);
        /////


//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
        //setupTabIcons();
        fregment_one.listyear = tb_ahkam_years;
        fregment_two.listtopics = ahkam_topic;
        fregment_one.ListtYPE = "نقض";
        fregment_two.ListtYPE = "نقض";

//        if (titlname != "المحكمة الدستورية" && titlname != "القضاء الإداري" && titlname != "الإداريا العليا" && titlname != "مجلس الدولة") {
//            viewPager.setCurrentItem(1, true);
//            //            TabLayout.Tab tab = tabLayout.getTabAt(1);
////            tab.select();
//        } else {
//            viewPager.setCurrentItem(0, true);
////            TabLayout.Tab tab = tabLayout.getTabAt(0);
////            tab.select();
//        }

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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(Nkd_Master.this, "TTTT", Toast.LENGTH_SHORT).show();
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
//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("سنوات");
//        Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
//        tabOne.setTypeface(type2);
//        //tabLayout.getTabAt(0).setCustomView(tabOne);
//
//
//        if (titlname != "المحكمة الدستورية" && titlname != "القضاء الإداري" && titlname != "الإداريا العليا" && titlname != "مجلس الدولة") {
//
//            TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//            tabTwo.setText("موضوعي");
//            Typeface type3 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
//            tabTwo.setTypeface(type3);
//            //tabLayout.getTabAt(1).setCustomView(tabTwo);
//        }
//
//    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new fregment_one());
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);

        if (titlname != "أحكام الدستورية العليا" && titlname != "محكمة القضاء الإداري"
                && titlname != "المحكمة الإدارية العليا" && titlname != "فتاوي مجلس الدولة") {
            adapter.addFrag(new fregment_two());
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
        }


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
        //private final List<String> mFragmentTitleList = new ArrayList<>();

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
            //mFragmentTitleList.add(title);
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
    }
}