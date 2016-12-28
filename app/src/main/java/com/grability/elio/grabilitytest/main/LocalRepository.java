package com.grability.elio.grabilitytest.main;

import com.grability.elio.grabilitytest.api.ApiClient;
import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;

import io.realm.RealmResults;

/**
 * Created by elio on 12/27/16.
 */
public interface LocalRepository {
    void loadApps();
    void saveApp(ApiClient.Entry entry);

    RealmResults<Category> getCategories();
    RealmResults<App> getApps();
}
