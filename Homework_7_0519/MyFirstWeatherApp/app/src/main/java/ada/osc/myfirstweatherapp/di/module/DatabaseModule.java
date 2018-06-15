package ada.osc.myfirstweatherapp.di.module;

import android.content.Context;

import ada.osc.myfirstweatherapp.persistance.LocationDao;
import ada.osc.myfirstweatherapp.persistance.LocationRoomDatabase;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Toni on 6/14/2018.
 */

@Module(includes = {AppModule.class})
public class DatabaseModule {

    @Provides
    public LocationRoomDatabase provideLocationRoomDatabase(Context context){
        return LocationRoomDatabase.getDatabase(context);
    }

    @Provides
    public LocationDao provideLocationDao(LocationRoomDatabase locationRoomDatabase){
        return locationRoomDatabase.locationDao();
    }

}
