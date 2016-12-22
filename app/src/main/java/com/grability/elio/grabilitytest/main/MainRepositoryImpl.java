package com.grability.elio.grabilitytest.main;

import com.grability.elio.grabilitytest.api.ApiClient;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.events.MainEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositoryImpl implements MainRepository {
    private EventBus eventBus;
    private MainEvent event;

    public MainRepositoryImpl(EventBus eventBus, MainEvent event) {
        this.eventBus = eventBus;
        this.event = event;
    }


    @Override
    public void loadApps() {
        Call<ApiClient.Objs> call = ApiClient.getObjects();
        call.enqueue(new Callback<ApiClient.Objs>() {
            @Override
            public void onResponse(Call<ApiClient.Objs> call, Response<ApiClient.Objs> response) {
                event.setType(MainEvent.onLoadAppsSuccess);
                event.setError(null);
                event.setData(response.body().feed.entry);
                eventBus.post(event);
            }

            @Override
            public void onFailure(Call<ApiClient.Objs> call, Throwable t) {
                event.setType(MainEvent.onLoadAppsError);
                event.setError(t.getLocalizedMessage());
                event.setData(null);
                eventBus.post(event);
            }
        });
    }
}
