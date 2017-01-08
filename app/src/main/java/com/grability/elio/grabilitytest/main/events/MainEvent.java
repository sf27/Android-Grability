package com.grability.elio.grabilitytest.main.events;

import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;

import io.realm.RealmResults;

public class MainEvent {
    public final static int onLoadDataLocalSuccess = 1;
    public final static int onLoadDataNetworkSuccess = 2;
    public final static int onLoadAppsError = 3;
    public final static int onLoadDetailsApp = 4;

    private int type;
    private String error;
    private RealmResults<App> apps;
    private RealmResults<Category> categories;
    private App app;

    public MainEvent() {
        this.error = null;
        this.type = 0;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public RealmResults<App> getApps() {
        return apps;
    }

    public void setApps(RealmResults<App> apps) {
        this.apps = apps;
    }

    public RealmResults<Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmResults<Category> categories) {
        this.categories = categories;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}