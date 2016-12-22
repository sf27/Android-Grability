package com.grability.elio.grabilitytest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.grability.elio.grabilitytest.api.ApiClient;
import com.grability.elio.grabilitytest.lib.GreenRobotEventBus;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.MainInteractor;
import com.grability.elio.grabilitytest.main.MainInteractorImpl;
import com.grability.elio.grabilitytest.main.MainPresenter;
import com.grability.elio.grabilitytest.main.MainPresenterImpl;
import com.grability.elio.grabilitytest.main.MainRepository;
import com.grability.elio.grabilitytest.main.MainRepositoryImpl;
import com.grability.elio.grabilitytest.main.UI.MainView;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainView {

    private MainPresenter mainPresenter;
    @BindView(R.id.txtHello)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        title.setText("Hello world!");
        EventBus eventBus = new GreenRobotEventBus();
        MainRepository mainRepository = new MainRepositoryImpl(eventBus, new MainEvent());
        MainInteractor mainInteractor = new MainInteractorImpl(mainRepository);
        mainPresenter = new MainPresenterImpl(this, eventBus, this, mainInteractor);
        mainPresenter.onCreate();
        mainPresenter.loadApps();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void downloadError(String error) {
        title.setText(error);
    }

    @Override
    public void showData(List<ApiClient.Entry> entries) {
        title.setText(entries.toString());
    }
}
