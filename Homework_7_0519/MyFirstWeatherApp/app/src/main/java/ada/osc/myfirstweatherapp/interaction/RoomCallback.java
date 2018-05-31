package ada.osc.myfirstweatherapp.interaction;

import java.util.List;

import ada.osc.myfirstweatherapp.model.Location;

/**
 * Created by Toni on 5/30/2018.
 */

public interface RoomCallback {

    void onLocationAdded();

    void onReadLocationsSuccess(List<Location> locations);

    void onLocationDuplicateCheckFinished();
}
