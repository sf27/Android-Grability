package com.grability.elio.grabilitytest.main;

import android.content.Context;

import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.UI.MainView;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import org.greenrobot.eventbus.Subscribe;

import io.realm.RealmResults;

public class MainPresenterImpl implements MainPresenter {

    private EventBus eventBus;
    private MainView mainView;
    private MainInteractor mainInteractor;
    private Context context;

    public MainPresenterImpl(Context context, EventBus eventBus, MainView mainView, MainInteractor mainInteractor) {
        this.context = context;
        this.eventBus = eventBus;
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mainView = null;
        eventBus.unregister(this);
    }

    @Override
    public void loadApps() {
        this.mainInteractor.loadApps();
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
        switch (event.getType()) {
            case MainEvent.onLoadAppsSuccess:
                onLoadAppsSuccess(event.getApps());
                onLoadCategoriesSuccess(event.getCategories());
                break;
            case MainEvent.onLoadAppsError:
                onDownloadError(event.getError());
                break;
        }
    }

    private void onDownloadError(String error) {
        if (mainView != null) {
            mainView.downloadError(error);
        }
    }

    private void onLoadAppsSuccess(RealmResults<App> apps) {
        if (mainView != null) {
            mainView.loadApps(apps);
        }
    }

    private void onLoadCategoriesSuccess(RealmResults<Category> categories) {
        if (mainView != null) {
            mainView.loadCategories(categories);
        }
    }

    @Override
    public MainView getView() {
        return this.mainView;
    }
}
