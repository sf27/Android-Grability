package com.grability.elio.grabilitytest.lib.di;

import com.grability.elio.grabilitytest.lib.GreenRobotEventBus;
import com.grability.elio.grabilitytest.lib.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by elio on 12/27/16.
 */
@Module
public class LibsModule {
    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
