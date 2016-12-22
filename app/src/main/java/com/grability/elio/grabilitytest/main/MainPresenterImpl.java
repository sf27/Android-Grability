package com.grability.elio.grabilitytest.main;

import android.content.Context;

import com.grability.elio.grabilitytest.api.ApiClient;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.UI.MainView;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

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
                onLoadAppsSuccess(event.getData());
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

    private void onLoadAppsSuccess(List<ApiClient.Entry> data) {
        if (mainView != null) {
            mainView.showData(data);
        }
    }

    @Override
    public MainView getView() {
        return this.mainView;
    }
}
