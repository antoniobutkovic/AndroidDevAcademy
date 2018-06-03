package ada.osc.myfirstweatherapp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.view.weather.WeatherFragment;

public class LocationPagerAdapter extends FragmentPagerAdapter {

    private final List<Location> locationsList = new ArrayList<>();

    public LocationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(locationsList.get(position).getLocation());
    }

    @Override
    public int getCount() {
        return locationsList.size();
    }

    public void setAdapterData(List<Location> dataSource) {
        locationsList.clear();
        locationsList.addAll(dataSource);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return locationsList.get(position).getLocation();
    }
}