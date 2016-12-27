package com.grability.elio.grabilitytest.main.UI;

import com.grability.elio.grabilitytest.api.ApiClient;
import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;

import java.util.List;

import io.realm.RealmResults;

public interface MainView {

    void downloadError(String error);

    void loadApps(RealmResults<App> apps);
    void loadCategories(RealmResults<Category> categories);
}
