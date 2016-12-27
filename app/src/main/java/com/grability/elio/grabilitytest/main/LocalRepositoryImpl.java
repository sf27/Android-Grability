package com.grability.elio.grabilitytest.main;

import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by elio on 12/26/16.
 */

public class LocalRepositoryImpl implements MainRepository {
    private EventBus eventBus;
    private MainEvent event;

    public LocalRepositoryImpl(EventBus eventBus, MainEvent event) {
        this.eventBus = eventBus;
        this.event = event;
    }

    @Override
    public void loadApps() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<App> apps = realm.where(App.class);
        RealmQuery<Category> categories = realm.where(Category.class);
        event.setType(MainEvent.onLoadAppsLocalSuccess);
        event.setError(null);
        event.setApps(apps.findAll());
        event.setCategories(categories.findAll());
        eventBus.post(event);
    }
}
