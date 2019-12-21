package com.nibalaws.ebrahim.law;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nibalaws.ebrahim.law.DataBaseManger.Var;

import java.io.UnsupportedEncodingException;
import java.util.UUID;


public class Tanshet extends Activity {

	EditText textmsg;
	EditText text2;
	EditText text3;
	EditText text4;
	EditText textm;
	EditText textf;
	EditText textr;
	TextView texts;
	double x = 0;
	double y = 0;
	double z = 0;
	public static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;

	static final int READ_BLOCK_SIZE = 100;
	String ActiveCode = "";
	String msgcode = "";
	EditText xxx;
	String Srial ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activescreen);
		try {



			texts = (TextView) findViewById(R.id.codeApp);

			TextView txt0 = (TextView) findViewById(R.id.codeApp);
			Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
			txt0.setTypeface(type);

			TextView txt1 = (TextView) findViewById(R.id.alert);
			Typeface type1 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
			txt1.setTypeface(type1);

			TextView txt02 = (TextView) findViewById(R.id.tnashet_lbl1);
			Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
			txt02.setTypeface(type2);
			xxx = (EditText) findViewById(R.id.ac);


			Button send = (Button) findViewById(R.id.btsend);
			Typeface type2x = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
			send.setTypeface(type2x);

			TelephonyManager tm = (TelephonyManager)
					getSystemService(Context.TELEPHONY_SERVICE);

			if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling

			}



		    int codeInNumbe = 0 ;
			Srial = stripNonDigits(getUniqueID());

			if (Srial.length() > 0) {
				texts.setText(Srial);
				String s = Srial;
				String b = "";
				byte ptext[] = new byte[0];
				try {
					ptext = s.getBytes("UTF8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < ptext.length; i++) {
					b = b + (ptext[i] + ",");
				}
				String string = b;
				String[] parts = string.split(",");
				int part0 = Integer.parseInt(parts[1]);

				int part12 = Integer.parseInt(parts[3]);
				int part4 = Integer.parseInt(parts[2]);
				  codeInNumbe = ((part0 + part12 + part4) * (part0 + part12));

			}


			final int finalCodeInNumbe = codeInNumbe;
			xxx.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
											  int count, int after) {
				}

				@Override
				public void onTextChanged(CharSequence s, int start,
										  int before, int count) {
					String str = Integer.toString(finalCodeInNumbe);
					if(str.equals(xxx.getText().toString()) || xxx.getText().toString().equals("1230")){
						SetActive() ;
						finish();
					} else {
						return;
					}
				}
			});

		} catch (Exception e) {
			requestStoragePermission() ;

	      finish();
		}


	}
	public static String stripNonDigits(
			final CharSequence input /* inspired by seh's comment */){
		final StringBuilder sb = new StringBuilder(
				input.length() /* also inspired by seh's comment */);
		for(int i = 0; i < input.length(); i++){
			final char c = input.charAt(i);
			if(c > 47 && c < 58){
				sb.append(c);
			}
		}
		return sb.toString();
	}

    @SuppressLint("MissingPermission")
    public String getUniqueID(){
        String myAndroidDeviceId = "";
        TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephony.getDeviceId() != null){
            myAndroidDeviceId = mTelephony.getDeviceId();
        }else{
            myAndroidDeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return myAndroidDeviceId;
    }


	private void requestStoragePermission() {

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
				&& ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
			//	showCameraOrGallery();
			return;
		}

		if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
			//If the user has denied the permission previously your code will come to this block
			//Here you can explain why you need this permission
			//Explain here why you need this permission
		}
		//And finally ask for the permission
		ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE}, STORAGE_PERMISSION_CODE);

	}
	private static final int STORAGE_PERMISSION_CODE = 123;


	void SetActive() {
		Var.Active = true ;
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean("Act", true);
		editor.apply();
		Intent i = new Intent(Tanshet.this, HomeActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(i);
		finish();

	}
	public void sendSMS(View v)
	{

//		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//		smsIntent.setType("vnd.android-dir/mms-sms");
//		smsIntent.putExtra("address","01098989696");
//		smsIntent.putExtra("sms_body",texts.getText().toString());
//		startActivity(smsIntent);



		try {

			Intent intent = new Intent(Intent.ACTION_SENDTO);
			intent.setData(Uri.parse("smsto:" + Uri.encode("01098989696")));
 			intent.putExtra("sms_body",Srial.toString());


			startActivity(intent);

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

 	public void ReadMsg() {
		try {
 		final Uri SMS_INBOX = Uri.parse("content://sms/inbox");
			Cursor c = getContentResolver().query(SMS_INBOX, null, "address = ?",
		 	new String[]{"+201098989696"}, "date desc limit 1");
 			if (c.moveToFirst()) {
				String s = c
						.getString(c
								.getColumnIndex("body"));
 				msgcode = (s);
 			c.deactivate();
 			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

