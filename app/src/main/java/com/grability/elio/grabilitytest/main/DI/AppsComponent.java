package com.grability.elio.grabilitytest.main.DI;

import com.grability.elio.grabilitytest.lib.DI.LibsModule;
import com.grability.elio.grabilitytest.main.AppDetailsPresenter;
import com.grability.elio.grabilitytest.main.MainPresenter;
import com.grability.elio.grabilitytest.main.adapters.AppsRecyclerAdapter;
import com.grability.elio.grabilitytest.main.adapters.CategoriesRecyclerAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by elio on 12/27/16.
 */
@Singleton
@Component(modules = {AppsModule.class, AppDetailsModule.class, LibsModule.class})
public interface AppsComponent {
    MainPresenter getMainPresenter();

    AppDetailsPresenter getAppDetailsPresenter();

    AppsRecyclerAdapter getAppsAdapter();

    CategoriesRecyclerAdapter getCategoriesAdapter();
}
