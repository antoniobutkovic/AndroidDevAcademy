package ada.osc.myfirstweatherapp.persistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ada.osc.myfirstweatherapp.model.Location;

/**
 * Created by Toni on 5/29/2018.
 */

@Dao
public interface LocationDao {

    @Insert
    void insert(Location location);

    @Query("SELECT * from location_table")
    List<Location> getAllLocations();

    @Query("SELECT * from location_table where location = :location")
    boolean isLocationDuplicated(String location);

}
