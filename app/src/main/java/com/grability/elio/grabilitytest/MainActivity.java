package com.grability.elio.grabilitytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.grability.elio.grabilitytest.api.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtHello)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        title.setText("Hello world!");

        Call<ApiClient.Objs> call = ApiClient.getObjects();
        call.enqueue(new Callback<ApiClient.Objs>() {
            @Override
            public void onResponse(Call<ApiClient.Objs> call, Response<ApiClient.Objs> response) {
                title.setText(response.body().feed.entry.get(0).title.label);
            }

            @Override
            public void onFailure(Call<ApiClient.Objs> call, Throwable t) {
            }
        });
    }
}
