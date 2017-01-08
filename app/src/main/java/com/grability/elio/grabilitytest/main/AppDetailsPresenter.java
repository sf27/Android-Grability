package com.grability.elio.grabilitytest.main;

import com.grability.elio.grabilitytest.main.UI.AppDetailsView;
import com.grability.elio.grabilitytest.main.events.MainEvent;

/**
 * Created by elio on 1/8/17.
 */

public interface AppDetailsPresenter {
    void onCreate();

    void onDestroy();

    void onEventMainThread(MainEvent event);

    AppDetailsView getView();

    void getAppById(String id);
}
