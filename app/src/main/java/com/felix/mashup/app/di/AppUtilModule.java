package com.felix.mashup.app.di;

import com.felix.mashup.EventBusIndex;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Module
class AppUtilModule {

    @Singleton
    @Provides
    EventBus provideEventBus() {
        return EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .eventInheritance(true)
                .addIndex(new EventBusIndex())
                .installDefaultEventBus();
    }
}
