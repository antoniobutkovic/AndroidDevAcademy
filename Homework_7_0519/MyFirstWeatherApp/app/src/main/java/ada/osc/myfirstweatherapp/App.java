package ada.osc.myfirstweatherapp;

import android.app.Application;

import ada.osc.myfirstweatherapp.di.component.AppComponent;
import ada.osc.myfirstweatherapp.di.component.DaggerAppComponent;
import ada.osc.myfirstweatherapp.di.module.AppModule;

/**
 * Created by Filip on 01/04/2016.
 */
public class App extends Application {

    private static AppComponent component;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public static AppComponent getComponent() {
        return component;
    }

    public static App getInstance() {
        return instance;
    }

}
