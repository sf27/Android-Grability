package com.grability.elio.grabilitytest.main.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.grability.elio.grabilitytest.entities.App;
import com.grability.elio.grabilitytest.entities.Category;
import com.grability.elio.grabilitytest.lib.base.EventBus;
import com.grability.elio.grabilitytest.main.LocalRepository;
import com.grability.elio.grabilitytest.main.LocalRepositoryImpl;
import com.grability.elio.grabilitytest.main.MainInteractor;
import com.grability.elio.grabilitytest.main.MainInteractorImpl;
import com.grability.elio.grabilitytest.main.MainPresenter;
import com.grability.elio.grabilitytest.main.MainPresenterImpl;
import com.grability.elio.grabilitytest.main.MainRepository;
import com.grability.elio.grabilitytest.main.NetworkRepositoryImpl;
import com.grability.elio.grabilitytest.main.UI.MainView;
import com.grability.elio.grabilitytest.main.adapters.AppsRecyclerAdapter;
import com.grability.elio.grabilitytest.main.adapters.CategoriesRecyclerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;

/**
 * Created by elio on 12/27/16.
 */
@Module
public class AppsModule {
    private Context context;
    private Fragment fragment;
    private MainView mainView;

    public AppsModule(Context context, Fragment fragment, MainView mainView) {
        this.context = context;
        this.fragment = fragment;
        this.mainView = mainView;
    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return this.fragment;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return this.context;
    }

    @Provides
    @Singleton
    MainView providesMainView() {
        return this.mainView;
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(Context context, EventBus eventBus, MainView mainView, MainInteractor mainInteractor) {
        return new MainPresenterImpl(context, eventBus, mainView, mainInteractor);
    }

    @Provides
    @Singleton
    MainInteractor providesMainInteractor(Context context, LocalRepository localRepository, MainRepository networkRepository) {
        return new MainInteractorImpl(context, localRepository, networkRepository);
    }

    @Provides
    @Singleton
    LocalRepository providesLocalRepository(EventBus eventBus, Realm realm) {
        return new LocalRepositoryImpl(eventBus, realm);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository(EventBus eventBus, LocalRepository localRepository) {
        return new NetworkRepositoryImpl(eventBus, localRepository);
    }

    @Provides
    @Singleton
    AppsRecyclerAdapter providesAppsAdapter(Context context, OrderedRealmCollection<App> data) {
        return new AppsRecyclerAdapter(context, data, true);
    }

    @Provides
    @Singleton
    CategoriesRecyclerAdapter providesCategoriesAdapter(Context context, OrderedRealmCollection<Category> data) {
        return new CategoriesRecyclerAdapter(context, data, true);
    }

    @Provides
    @Singleton
    OrderedRealmCollection<App> providesAppOrderedRealmCollection () {
        return Realm.getDefaultInstance().where(App.class).findAll();
    }

    @Provides
    @Singleton
    OrderedRealmCollection<Category> providesCategoryOrderedRealmCollection () {
        return Realm.getDefaultInstance().where(Category.class).findAll();
    }

    @Provides
    @Singleton
    Realm providesRealm() {
        return Realm.getDefaultInstance();
    }
}
