package com.grability.elio.grabilitytest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.grability.elio.grabilitytest.lib.DI.LibsModule;
import com.grability.elio.grabilitytest.main.DI.AppDetailsModule;
import com.grability.elio.grabilitytest.main.UI.AppDetailsView;
import com.grability.elio.grabilitytest.main.UI.MainView;
import com.grability.elio.grabilitytest.main.DI.AppsComponent;
import com.grability.elio.grabilitytest.main.DI.AppsModule;
import com.grability.elio.grabilitytest.main.DI.DaggerAppsComponent;

/**
 * Created by elio on 12/27/16.
 */
public class GrabilityApp extends Application {

    public AppsComponent getAppsComponent(Context context, Fragment fragment, MainView mainView) {
        return DaggerAppsComponent
                .builder()
                .libsModule(new LibsModule())
                .appsModule(new AppsModule(context, fragment, mainView))
                .appDetailsModule(new AppDetailsModule(context, fragment.getActivity(), null))
                .build();
    }

    public AppsComponent getAppDetailsComponent(Context context, Activity activity, AppDetailsView detailsView) {
        return DaggerAppsComponent
                .builder()
                .libsModule(new LibsModule())
                .appsModule(new AppsModule(context, null, null))
                .appDetailsModule(new AppDetailsModule(context, activity, detailsView))
                .build();
    }
}
