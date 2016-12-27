package com.grability.elio.grabilitytest.main.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.grability.elio.grabilitytest.R;
import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;
import com.grability.elio.grabilitytest.lib.GreenRobotEventBus;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.LocalRepositoryImpl;
import com.grability.elio.grabilitytest.main.MainInteractor;
import com.grability.elio.grabilitytest.main.MainInteractorImpl;
import com.grability.elio.grabilitytest.main.MainPresenter;
import com.grability.elio.grabilitytest.main.MainPresenterImpl;
import com.grability.elio.grabilitytest.main.MainRepository;
import com.grability.elio.grabilitytest.main.NetworkRepositoryImpl;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;


public class CategoriesFragment extends Fragment implements MainView {

    private MainPresenter mainPresenter;
    @BindView(R.id.textCategories)
    TextView textCategories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);

        EventBus eventBus = new GreenRobotEventBus();
        MainRepository networkRepository = new NetworkRepositoryImpl(eventBus, new MainEvent());
        MainRepository localRepository = new LocalRepositoryImpl(eventBus, new MainEvent());
        MainInteractor mainInteractor = new MainInteractorImpl(
                getActivity(), localRepository, networkRepository
        );
        mainPresenter = new MainPresenterImpl(
                getActivity(), eventBus, this, mainInteractor
        );
        mainPresenter.onCreate();
        mainPresenter.loadApps();

        return view;
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
        System.out.println("CategoriesResult: " + categories);
        textCategories.setText(categories.toString());
    }

    @Override
    public void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }
}