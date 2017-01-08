package com.grability.elio.grabilitytest.main.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grability.elio.grabilitytest.GrabilityApp;
import com.grability.elio.grabilitytest.R;
import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.main.AppDetailsPresenter;
import com.grability.elio.grabilitytest.main.DI.AppsComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailsActivity extends AppCompatActivity implements AppDetailsView {

    private AppDetailsPresenter presenter;
    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtCategory)
    TextView txtCategory;
    @BindView(R.id.txtDescription)
    TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupInjection();

        presenter.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String app_id = intent.getStringExtra("app_id");
        presenter.getAppById(app_id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }


    public void setupInjection() {
        GrabilityApp app = new GrabilityApp();
        AppsComponent component = app.getAppDetailsComponent(AppDetailsActivity.this, this, this);
        presenter = component.getAppDetailsPresenter();
    }

    @Override
    public void loadAppById(App app) {
        setTitle(app.getTitle());
        txtName.setText(app.getTitle());
        txtDescription.setText(app.getDescription());
        txtCategory.setText(app.getCategory().getName());
        Glide.with(AppDetailsActivity.this)
                .load(app.getImages().get(2).getUrl())
                .override(100, 100)
                .fitCenter()
                .into(imgLogo);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
