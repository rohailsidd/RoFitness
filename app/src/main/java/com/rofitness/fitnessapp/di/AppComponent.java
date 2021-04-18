package com.rofitness.fitnessapp.di;

import android.app.Application;

import com.rofitness.fitnessapp.FitnessApp;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, RoomModule.class, UiComponentsModule.class, ViewModelModule.class})
@Singleton
public interface AppComponent extends AndroidInjector<FitnessApp> {

    void inject(FitnessApp fitnessApp);

    @Component.Builder
    public interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
