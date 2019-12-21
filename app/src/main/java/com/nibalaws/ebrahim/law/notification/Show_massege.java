package com.nibalaws.ebrahim.law.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.Splash_Screen;

public class Show_massege extends AppCompatActivity {
    public static  String txtmssage ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_massege);
        Button homeclick= (Button) findViewById(R.id.bt_show);
        TextView textView = (TextView) findViewById(R.id.txt);
        textView.setText(txtmssage);
        homeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Splash_Screen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });

    }
}
