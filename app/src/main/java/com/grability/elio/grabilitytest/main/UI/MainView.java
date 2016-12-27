package com.grability.elio.grabilitytest.main.UI;

import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;

import io.realm.RealmResults;

public interface MainView {

    void onDownloadError(String error);

    void onLostNetworkConnectionError(String error);

    void onLoadApps(RealmResults<App> apps);

    void onLoadCategories(RealmResults<Category> categories);
}
