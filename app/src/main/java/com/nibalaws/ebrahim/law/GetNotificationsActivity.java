package com.nibalaws.ebrahim.law;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nibalaws.ebrahim.law.rest.APIManager;
import com.nibalaws.ebrahim.law.rest.apiModel.GetNotificationsResponse;
import com.nibalaws.ebrahim.law.util.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetNotificationsActivity extends AppCompatActivity {

    @BindView(R.id.EgyptTash_back_txt)
    TextView EgyptTashBackTxt;
    @BindView(R.id.txtTitel)
    TextView txtTitel;

    private ImageView IV_Notification;
    private TextView TV_NotificationTitle, TV_NotificationDescription;

    private void setViewsTypeface() {
        Util.setViewsTypeface(this, txtTitel);
        Util.setViewsTypeface(this, TV_NotificationTitle);
        Util.setViewsTypeface(this, TV_NotificationDescription);
        Util.setViewsTypeface(this, EgyptTashBackTxt);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.activity_get_notifications);
        ButterKnife.bind(this);

        IV_Notification = findViewById(R.id.IV_Notification);
        TV_NotificationTitle = findViewById(R.id.TV_NotificationTitle);
        TV_NotificationDescription = findViewById(R.id.TV_NotificationDescription);

        setViewsTypeface();

        loadNotifications();
    }

    private void loadNotifications() {
        APIManager.getApis().getNotifications()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<GetNotificationsResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<GetNotificationsResponse> getNotificationsResponse) {
                        for (int i = 0; i < getNotificationsResponse.size(); i++) {
                            Picasso.get().load(getNotificationsResponse.get(i).getImg()).into(IV_Notification);
                            TV_NotificationTitle.setText(getNotificationsResponse.get(i).getTitle());
                            TV_NotificationDescription.setText(getNotificationsResponse.get(i).getDescription());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(GetNotificationsActivity.this, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @OnClick({R.id.homeclick, R.id.backclik})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeclick:
                Util.openActivity(this, HomeActivity.class);
                break;
            case R.id.backclik:
                finish();
                break;
        }
    }

}
