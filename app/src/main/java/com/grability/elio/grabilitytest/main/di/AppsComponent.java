package com.grability.elio.grabilitytest.main.di;

import com.grability.elio.grabilitytest.lib.di.LibsModule;
import com.grability.elio.grabilitytest.main.MainPresenter;
import com.grability.elio.grabilitytest.main.adapters.AppsRecyclerAdapter;
import com.grability.elio.grabilitytest.main.adapters.CategoriesRecyclerAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by elio on 12/27/16.
 */
@Singleton
@Component(modules = {AppsModule.class, LibsModule.class})
public interface AppsComponent {
    MainPresenter getMainPresenter();
    AppsRecyclerAdapter getAppsAdapter();
    CategoriesRecyclerAdapter getCategoriesAdapter();
}
