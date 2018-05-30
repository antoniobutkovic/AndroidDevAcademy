package ada.osc.myfirstweatherapp;

import android.app.Application;
import android.support.annotation.NonNull;

import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.interaction.ApiInteractorImpl;
import ada.osc.myfirstweatherapp.interaction.RoomInteractor;
import ada.osc.myfirstweatherapp.interaction.RoomInteractorImpl;
import ada.osc.myfirstweatherapp.network.ApiService;
import ada.osc.myfirstweatherapp.network.RetrofitUtil;
import ada.osc.myfirstweatherapp.persistance.LocationDao;
import ada.osc.myfirstweatherapp.persistance.LocationRoomDatabase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Filip on 01/04/2016.
 */
public class App extends Application {

    private static RoomInteractor roomInteractor;
    private static ApiInteractor apiInteractor;

    @Override
    public void onCreate() {
        super.onCreate();

        final LocationRoomDatabase database = LocationRoomDatabase.getDatabase(this);
        final LocationDao locationDao = database.locationDao();

        final Retrofit retrofit = RetrofitUtil.createRetrofit();
        final ApiService apiService = retrofit.create(ApiService.class);

        apiInteractor = new ApiInteractorImpl(apiService);
        roomInteractor = new RoomInteractorImpl(locationDao);

    }

    public static RoomInteractor getRoomInteractor() {
        return roomInteractor;
    }

    public static ApiInteractor getApiInteractor() {
        return apiInteractor;
    }

}
