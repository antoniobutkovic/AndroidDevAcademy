package ada.osc.myfirstweatherapp.di.module;

import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.interaction.ApiInteractorImpl;
import ada.osc.myfirstweatherapp.interaction.RoomInteractor;
import ada.osc.myfirstweatherapp.interaction.RoomInteractorImpl;
import ada.osc.myfirstweatherapp.network.ApiService;
import ada.osc.myfirstweatherapp.persistance.LocationDao;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Toni on 6/14/2018.
 */

@Module(includes = {DatabaseModule.class, NetworkModule.class})
public class InteractorModule {

    @Provides
    public ApiInteractor provideApiInteractor(ApiService apiService){
        return new ApiInteractorImpl(apiService);
    }

    @Provides
    public RoomInteractor provideRoomInteractor(LocationDao locationDao){
        return new RoomInteractorImpl(locationDao);
    }

}
