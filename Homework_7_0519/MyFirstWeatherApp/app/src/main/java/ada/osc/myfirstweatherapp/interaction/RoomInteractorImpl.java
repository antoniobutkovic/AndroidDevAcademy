package ada.osc.myfirstweatherapp.interaction;

import android.util.Log;

import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.persistance.LocationDao;

/**
 * Created by Toni on 5/29/2018.
 */

public class RoomInteractorImpl implements RoomInteractor{

    private final LocationDao locationDao;

    public RoomInteractorImpl(LocationDao locationDao){
         this.locationDao = locationDao;
    }

    @Override
    public void insertLocation(RoomCallback roomCallback, Location location) {
        locationDao.insert(location);
        roomCallback.onLocationAdded();
    }

    @Override
    public void getAllLocations(RoomCallback roomCallback) {
        roomCallback.onReadLocationsSuccess(locationDao.getAllLocations());
    }

    @Override
    public boolean isLocationDuplicated(String location) {
        return locationDao.isLocationDuplicated(location);
    }
}
