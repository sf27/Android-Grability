package com.grability.elio.grabilitytest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.grability.elio.grabilitytest.main.UI.AppsFragment;
import com.grability.elio.grabilitytest.main.UI.CategoriesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

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

        Fragment fragment = new AppsFragment();
        setFragmentContent(fragment);

        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        Realm.init(MainActivity.this);
    }

    public void setUpNavigation() {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    Fragment fragment;
                    switch (menuItem.getItemId()) {
                        case R.id.drawer_action_apps:
                            fragment = new AppsFragment();
                            setFragmentContent(fragment);
                            break;
                        case R.id.drawer_action_categories:
                            fragment = new CategoriesFragment();
                            setFragmentContent(fragment);
                            break;
                        default:
                            break;
                    }
                    drawerLayout.closeDrawers();
                    return true;
                }
        );
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

    private void setFragmentContent(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_content, fragment);
        fragmentTransaction.commit();
    }
}
