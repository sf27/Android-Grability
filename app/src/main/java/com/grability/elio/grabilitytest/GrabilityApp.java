package com.grability.elio.grabilitytest;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.grability.elio.grabilitytest.lib.di.LibsModule;
import com.grability.elio.grabilitytest.main.UI.MainView;
import com.grability.elio.grabilitytest.main.di.AppsComponent;
import com.grability.elio.grabilitytest.main.di.AppsModule;
import com.grability.elio.grabilitytest.main.di.DaggerAppsComponent;

/**
 * Created by elio on 12/27/16.
 */
public class GrabilityApp extends Application {

    public AppsComponent getAppsComponent(Context context, Fragment fragment, MainView mainView) {
        return DaggerAppsComponent
                .builder()
                .libsModule(new LibsModule())
                .appsModule(new AppsModule(context, fragment, mainView))
                .build();
    }
}
