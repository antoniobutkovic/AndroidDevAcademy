package ada.osc.myfirstweatherapp.view.addLocation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.presentation.NewLocationPresenter;
import ada.osc.myfirstweatherapp.presentation.NewLocationPresenterImpl;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Filip on 10/02/2016.
 */
public class LocationFragment extends Fragment implements NewLocationView{

    private NewLocationPresenter presenter;

    @BindView(R.id.fragment_add_location_enter_city_edit_text)
    EditText cityEt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new NewLocationPresenterImpl();
        presenter.setView(this);
    }

    public void onSuccess() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.location_added_success_toast_message), Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    public void onLocationAlreadyExistsError() {
        cityEt.setError(getActivity().getString(R.string.location_already_exists_error_message));
    }

    public void onEmptyStringRequestError() {
        cityEt.setError(getActivity().getString(R.string.empty_location_string_error_message));
    }

    @OnClick(R.id.fragment_add_location_button)
    public void onAddLocationBtnClicked(){
        addNewLocation();
    }

    private void addNewLocation() {
        String city = cityEt.getText().toString();
        Location newLocation = new Location(city);
        presenter.addNewLocation(newLocation);
    }

}
