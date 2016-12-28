package com.grability.elio.grabilitytest.main;

import com.grability.elio.grabilitytest.api.ApiClient;
import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.AppImage;
import com.grability.elio.grabilitytest.entities.Category;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by elio on 12/26/16.
 */

public class LocalRepositoryImpl implements LocalRepository {
    private EventBus eventBus;
    private Realm realm;

    public LocalRepositoryImpl(EventBus eventBus, Realm realm) {
        this.eventBus = eventBus;
        this.realm = realm;
    }

    @Override
    public void loadApps() {
        RealmQuery<App> apps = realm.where(App.class);
        RealmQuery<Category> categories = realm.where(Category.class);
        MainEvent event = new MainEvent();
        event.setType(MainEvent.onLoadAppsLocalSuccess);
        event.setError(null);
        event.setApps(apps.findAll());
        event.setCategories(categories.findAll());
        eventBus.post(event);
    }

    @Override
    public void saveApp(ApiClient.Entry entry) {
        realm.executeTransaction(realm -> {
            Category category = new Category();
            category.setName(entry.category.attributes.label);

            App app = new App();
            app.setId(entry.id.attributes.id);
            app.setCategory(category);
            app.setDescription(entry.summary.label);
            app.setTitle(entry.title.label);

            RealmList<AppImage> appImages = new RealmList<>();
            for (ApiClient.Image image : entry.images) {
                AppImage appImage = new AppImage();
                appImage.setUrl(image.label);
                appImages.add(appImage);
            }
            app.setImages(appImages);

            realm.copyToRealmOrUpdate(app);
        });
    }

    public RealmResults<Category> getCategories() {
        RealmQuery<Category> categories = realm.where(Category.class);
        return categories.findAll();
    }

    public RealmResults<App> getApps() {
        RealmQuery<App> apps = realm.where(App.class);
        return apps.findAll();
    }
}
