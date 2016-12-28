package com.grability.elio.grabilitytest.main.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.grability.elio.grabilitytest.GrabilityApp;
import com.grability.elio.grabilitytest.R;
import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;
import com.grability.elio.grabilitytest.main.MainPresenter;
import com.grability.elio.grabilitytest.main.adapters.AppsRecyclerAdapter;
import com.grability.elio.grabilitytest.main.di.AppsComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;


public class AppsFragment extends Fragment implements MainView {

    @BindView(R.id.appRecyclerView)
    RecyclerView appRecyclerView;

    AppsComponent component;
    MainPresenter presenter;
    AppsRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps, container, false);
        ButterKnife.bind(this, view);

        setupInjection();

        appRecyclerView.setAdapter(adapter);
        appRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

    public void setupInjection() {
        GrabilityApp app = new GrabilityApp();
        component = app.getAppsComponent(getActivity(), this, this);
        presenter = component.getMainPresenter();
        adapter = component.getAppsAdapter();
    }

    @Override
    public void onDownloadError(String error) {
        System.out.println("ErrorApp: " + error);
    }

    @Override
    public void onLostNetworkConnectionError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadApps(RealmResults<App> apps) {
        adapter = new AppsRecyclerAdapter(getActivity(), apps, true);
        appRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadCategories(RealmResults<Category> categories) {
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
