package com.grability.elio.grabilitytest.main;

import com.grability.elio.grabilitytest.main.UI.MainView;
import com.grability.elio.grabilitytest.main.events.MainEvent;

public interface MainPresenter {
    void onCreate();

    void onDestroy();

    void loadApps();

    void onEventMainThread(MainEvent event);

    MainView getView();
}
