package com.grability.elio.grabilitytest.main;

import com.grability.elio.grabilitytest.api.ApiClient;
import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import java.util.ArrayList;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepositoryImpl implements MainRepository {
    private EventBus eventBus;
    private LocalRepository localRepository;

    public NetworkRepositoryImpl(EventBus eventBus, LocalRepository localRepository) {
        this.eventBus = eventBus;
        this.localRepository = localRepository;
    }

    @Override
    public void loadApps() {
        MainEvent event = new MainEvent();
        Call<ApiClient.Objs> call = ApiClient.getObjects();
        call.enqueue(new Callback<ApiClient.Objs>() {
            @Override
            public void onResponse(Call<ApiClient.Objs> call, Response<ApiClient.Objs> response) {
                ArrayList<ApiClient.Entry> entries = (ArrayList<ApiClient.Entry>)
                        response.body().feed.entry;
                saveData(entries);

                event.setType(MainEvent.onLoadAppsNetworkSuccess);
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
            localRepository.saveApp(entry);
        }
    }

    private RealmResults<Category> getCategories() {
        return localRepository.getCategories();
    }

    private RealmResults<App> getApps() {
        return localRepository.getApps();
    }
}
