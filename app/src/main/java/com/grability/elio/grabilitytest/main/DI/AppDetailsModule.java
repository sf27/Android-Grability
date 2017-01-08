package com.grability.elio.grabilitytest.main.DI;

import android.app.Activity;
import android.content.Context;

import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.AppDetailsPresenter;
import com.grability.elio.grabilitytest.main.AppDetailsPresenterImpl;
import com.grability.elio.grabilitytest.main.MainInteractor;
import com.grability.elio.grabilitytest.main.UI.AppDetailsView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by elio on 12/27/16.
 */
@Module
public class AppDetailsModule {
    private Context context;
    private AppDetailsView detailsView;
    private Activity activity;

    public AppDetailsModule(Context context, Activity activity, AppDetailsView detailsView) {
        this.context = context;
        this.activity = activity;
        this.detailsView = detailsView;
    }

    @Provides
    @Singleton
    Activity providesActivity() {
        return this.activity;
    }

    @Provides
    @Singleton
    AppDetailsView providesAppDetailsView() {
        return this.detailsView;
    }

    @Provides
    @Singleton
    AppDetailsPresenter providesMainPresenter(Context context, EventBus eventBus, AppDetailsView detailsView, MainInteractor mainInteractor) {
        return new AppDetailsPresenterImpl(context, eventBus, detailsView, mainInteractor);
    }
}
