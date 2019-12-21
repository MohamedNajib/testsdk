package com.nibalaws.ebrahim.law;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nibalaws.ebrahim.law.ahkam.fregment.fregment_one;
import com.nibalaws.ebrahim.law.util.ViewPagerTapsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Ssssss extends AppCompatActivity {

//    @BindView(R.id.Home_Fragment_ViewPager)
//    ViewPager mViewPager;
//    @BindView(R.id.button3)
//    Button button3;
//    @BindView(R.id.button2)
//    Button button2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_civil_law);
//        setContentView(R.layout.layout_favorite);
//        setContentView(R.layout.layout_law_articles);
//        setContentView(R.layout.layout_egyptian_tash);
//        setContentView(R.layout.layout_call_us);
        setContentView(R.layout.activescreen);
        ButterKnife.bind(this);

//        mViewPager = findViewById(R.id.Home_Fragment_ViewPager);
//        setupClientViewPager(mViewPager);




    }

//
//    @OnClick({R.id.button3, R.id.button2})
//    public void onViewClicked(View view) {
//        GradientDrawable drawable1 = (GradientDrawable) button3.getBackground();
//        GradientDrawable drawable2 = (GradientDrawable) button2.getBackground();
//
//        switch (view.getId()) {
//            case R.id.button3:
//                mViewPager.setCurrentItem(1, true);
//
//                drawable1.setStroke(2, getResources().getColor(R.color.a));
//                drawable2.setStroke(2, getResources().getColor(R.color.a));
//
//                drawable1.setColor(getResources().getColor(R.color.a));
//                drawable2.setColor(getResources().getColor(R.color.b));
//                button3.setTextColor(getResources().getColor(R.color.white));
//                button2.setTextColor(getResources().getColor(R.color.t));
//
//                break;
//            case R.id.button2:
//                mViewPager.setCurrentItem(0, true);
//                drawable1.setStroke(2, getResources().getColor(R.color.a));
//                drawable2.setStroke(2, getResources().getColor(R.color.a));
//
//                drawable1.setColor(getResources().getColor(R.color.b));
//                drawable2.setColor(getResources().getColor(R.color.a));
//
//                button2.setTextColor(getResources().getColor(R.color.white));
//                button3.setTextColor(getResources().getColor(R.color.t));
//                break;
//        }
//    }
//
//    private void setupClientViewPager(ViewPager viewPager) {
//        ViewPagerTapsAdapter adapter = new ViewPagerTapsAdapter(getSupportFragmentManager());
//        adapter.addFragment(new fregment_one(), "One");
//        adapter.addFragment(new BlankFragment(), "Two");
//
//        viewPager.setAdapter(adapter);
//    }

}
