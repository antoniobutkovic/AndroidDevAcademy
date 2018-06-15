package ada.osc.myfirstweatherapp.di.module;

import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.interaction.RoomInteractor;
import ada.osc.myfirstweatherapp.presentation.LocationPresenter;
import ada.osc.myfirstweatherapp.presentation.LocationPresenterImpl;
import ada.osc.myfirstweatherapp.presentation.NewLocationPresenter;
import ada.osc.myfirstweatherapp.presentation.NewLocationPresenterImpl;
import ada.osc.myfirstweatherapp.presentation.WeatherPresenter;
import ada.osc.myfirstweatherapp.presentation.WeatherPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Toni on 6/14/2018.
 */

@Module(includes = {InteractorModule.class})
public class PresenterModule {

    @Provides
    public LocationPresenter provideLocationPresenter(RoomInteractor roomInteractor){
        return new LocationPresenterImpl(roomInteractor);
    }

    @Provides
    public NewLocationPresenter provideNewLocationPresenter(RoomInteractor roomInteractor){
        return new NewLocationPresenterImpl(roomInteractor);
    }

    @Provides
    public WeatherPresenter provideWeatherPresenter(ApiInteractor apiInteractor){
        return new WeatherPresenterImpl(apiInteractor);
    }

}
