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
import com.grability.elio.grabilitytest.main.adapters.CategoriesRecyclerAdapter;
import com.grability.elio.grabilitytest.main.DI.AppsComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;


public class CategoriesFragment extends Fragment implements MainView {

    @BindView(R.id.categoriesRecyclerView)
    RecyclerView categoriesRecyclerView;

    MainPresenter presenter;
    AppsComponent component;
    CategoriesRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);

        setupInjection();

        categoriesRecyclerView.setAdapter(adapter);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        return view;
    }

    private void setupInjection() {
        GrabilityApp app = new GrabilityApp();
        component = app.getAppsComponent(getActivity(), this, this);
        presenter = component.getMainPresenter();
        adapter = component.getCategoriesAdapter();
    }

    @Override
    public void onDownloadError(String error) {
        System.out.println("ErrorCategories: " + error);
    }

    @Override
    public void onLostNetworkConnectionError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadApps(RealmResults<App> apps) {
    }

    @Override
    public void onLoadCategories(RealmResults<Category> categories) {
        adapter = new CategoriesRecyclerAdapter(getActivity(), categories, true);
        categoriesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
