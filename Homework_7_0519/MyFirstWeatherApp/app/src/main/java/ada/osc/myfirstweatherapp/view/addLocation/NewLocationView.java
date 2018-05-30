package ada.osc.myfirstweatherapp.view.addLocation;

import android.view.View;

/**
 * Created by Toni on 5/29/2018.
 */

public interface NewLocationView {

    void onSuccess();

    void onLocationAlreadyExistsError();

    void onEmptyStringRequestError();

}
