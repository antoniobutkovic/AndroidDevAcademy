package ada.osc.myfirstweatherapp.di.component;

import javax.inject.Singleton;

import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.di.module.AppModule;
import ada.osc.myfirstweatherapp.di.module.DatabaseModule;
import ada.osc.myfirstweatherapp.di.module.InteractorModule;
import ada.osc.myfirstweatherapp.di.module.PresenterModule;
import ada.osc.myfirstweatherapp.view.addLocation.LocationFragment;
import ada.osc.myfirstweatherapp.view.location.LocationActivity;
import ada.osc.myfirstweatherapp.view.weather.WeatherFragment;
import dagger.Component;
import dagger.Provides;

/**
 * Created by Toni on 6/5/2018.
 */

@Singleton
@Component(modules = {PresenterModule.class})
public interface AppComponent {

    void inject(App app);

    void inject(LocationActivity locationActivity);

    void inject(LocationFragment locationFragment);

    void inject(WeatherFragment weatherFragment);

}
