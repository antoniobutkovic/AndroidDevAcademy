package ada.osc.myfirstweatherapp;

import android.app.Application;
import android.support.annotation.NonNull;

import ada.osc.myfirstweatherapp.persistance.LocationDao;
import ada.osc.myfirstweatherapp.persistance.LocationRoomDatabase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Filip on 01/04/2016.
 */
public class App extends Application {

    private static App sInstance;
    private static Retrofit retrofit;
    private static LocationDao locationDao;

    @Override
    public void onCreate() {
        super.onCreate();

        LocationRoomDatabase database = LocationRoomDatabase.getDatabase(this);

        sInstance = this;
        retrofit = provideRestClient();
        locationDao = database.locationDao();
    }

    public static App getInstance() {
        return sInstance;
    }

    @NonNull
    private Retrofit provideRestClient() {
        return new Retrofit.Builder()
                .baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static LocationDao getLocationDao() {
        return locationDao;
    }
}
