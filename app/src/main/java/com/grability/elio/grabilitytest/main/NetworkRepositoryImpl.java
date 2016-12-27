package com.grability.elio.grabilitytest.main;

import com.grability.elio.grabilitytest.api.ApiClient;
import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.AppImage;
import com.grability.elio.grabilitytest.entities.Category;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepositoryImpl implements MainRepository {
    private EventBus eventBus;
    private MainEvent event;
    private Realm realm;

    public NetworkRepositoryImpl(EventBus eventBus, MainEvent event) {
        this.eventBus = eventBus;
        this.event = event;
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public void loadApps() {
        Call<ApiClient.Objs> call = ApiClient.getObjects();
        call.enqueue(new Callback<ApiClient.Objs>() {
            @Override
            public void onResponse(Call<ApiClient.Objs> call, Response<ApiClient.Objs> response) {
                ArrayList<ApiClient.Entry> entries = (ArrayList<ApiClient.Entry>)
                        response.body().feed.entry;
                saveData(entries);
                event.setType(MainEvent.onLoadAppsSuccess);
                event.setError(null);
                event.setApps(getApps());
                event.setCategories(getCategories());
                eventBus.post(event);
            }

            @Override
            public void onFailure(Call<ApiClient.Objs> call, Throwable t) {
                event.setType(MainEvent.onLoadAppsError);
                event.setError(t.getLocalizedMessage());
                event.setApps(null);
                event.setCategories(null);
                eventBus.post(event);
            }
        });
    }

    private void saveData(ArrayList<ApiClient.Entry> entries) {
        for (ApiClient.Entry entry : entries) {
            realm.beginTransaction();

            Category categoryEntity = realm.createObject(Category.class);
            categoryEntity.setName(entry.category.attributes.label);

            App appEntity = realm.createObject(App.class);
            appEntity.setCategory(categoryEntity);
            appEntity.setDescription(entry.summary.label);
            appEntity.setTitle(entry.title.label);

            for (ApiClient.Image image : entry.images) {
                AppImage appImageEntity = realm.createObject(AppImage.class);
                appImageEntity.setUrl(image.label);
                appEntity.images.add(appImageEntity);
            }

            realm.commitTransaction();
        }
    }

    private RealmResults<Category> getCategories(){
        RealmQuery<Category> categories = realm.where(Category.class);
        return categories.findAll();
    }

    private RealmResults<App> getApps(){
        RealmQuery<App> apps = realm.where(App.class);
        return apps.findAll();
    }
}
