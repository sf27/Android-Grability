package com.grability.elio.grabilitytest.main;

import android.content.Context;

import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.UI.AppDetailsView;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by elio on 1/8/17.
 */

public class AppDetailsPresenterImpl implements AppDetailsPresenter {

    private EventBus eventBus;
    private AppDetailsView detailsView;
    private MainInteractor mainInteractor;
    private Context context;

    public AppDetailsPresenterImpl(
            Context context, EventBus eventBus, AppDetailsView detailsView, MainInteractor mainInteractor) {
        this.context = context;
        this.eventBus = eventBus;
        this.detailsView = detailsView;
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        detailsView = null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
        switch (event.getType()) {
            case MainEvent.onLoadDetailsApp:
                this.detailsView.loadAppById(event.getApp());
                break;
        }
    }

    @Override
    public AppDetailsView getView() {
        return this.detailsView;
    }

    @Override
    public void getAppById(String id) {
        this.mainInteractor.getAppById(id);
    }
}
