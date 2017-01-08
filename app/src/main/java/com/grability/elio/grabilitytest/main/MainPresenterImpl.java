package com.grability.elio.grabilitytest.main;

import android.content.Context;

import com.grability.elio.grabilitytest.R;
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

    public MainPresenterImpl(
            Context context, EventBus eventBus, MainView mainView, MainInteractor mainInteractor) {
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
        System.out.println("onEventMainThread");
        switch (event.getType()) {
            case MainEvent.onLoadDataLocalSuccess:
                onLoadAppsSuccess(event.getApps());
                onLoadCategoriesSuccess(event.getCategories());
                onLostNetworkConnectionError(
                        context.getString(R.string.message_lost_network_connection)
                );
                break;
            case MainEvent.onLoadAppsError:
                onDownloadError(event.getError());
                break;
            case MainEvent.onLoadDataNetworkSuccess:
                onLoadAppsSuccess(event.getApps());
                onLoadCategoriesSuccess(event.getCategories());
                break;
        }
    }

    private void onDownloadError(String error) {
        if (mainView != null) {
            mainView.onDownloadError(error);
        }
    }

    private void onLostNetworkConnectionError(String error) {
        if (mainView != null) {
            mainView.onLostNetworkConnectionError(error);
        }
    }

    private void onLoadAppsSuccess(RealmResults<App> apps) {
        if (mainView != null) {
            mainView.onLoadApps(apps);
        }
    }

    private void onLoadCategoriesSuccess(RealmResults<Category> categories) {
        if (mainView != null) {
            mainView.onLoadCategories(categories);
        }
    }

    @Override
    public MainView getView() {
        return this.mainView;
    }
}
