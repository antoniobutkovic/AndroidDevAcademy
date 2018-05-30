package ada.osc.myfirstweatherapp.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ada.osc.myfirstweatherapp.model.Location;

/**
 * Created by Toni on 5/29/2018.
 */

@Database(entities = {Location.class}, version = 1)
public abstract class LocationRoomDatabase extends RoomDatabase {

    public abstract LocationDao locationDao();

    private static LocationRoomDatabase INSTANCE;

    public static LocationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocationRoomDatabase.class, "location_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}