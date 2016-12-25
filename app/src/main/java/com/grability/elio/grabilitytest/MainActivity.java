package com.grability.elio.grabilitytest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setUpNavigation();

        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

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
        System.out.println("Epale");
        return false;
    }

    @Override
    public void downloadError(String error) {
        System.out.println(error);
    }

    @Override
    public void showData(List<ApiClient.Entry> entries) {
        System.out.println(entries.toString());
    }

    public void setUpNavigation() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.drawer_action_apps:
                                break;
                            case R.id.drawer_action_categories:
                                break;
                            default:
                                break;
                        }
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}
