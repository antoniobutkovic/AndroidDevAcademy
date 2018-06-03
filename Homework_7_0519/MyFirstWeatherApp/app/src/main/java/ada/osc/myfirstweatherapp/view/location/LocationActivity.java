package ada.osc.myfirstweatherapp.view.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.presentation.LocationPresenter;
import ada.osc.myfirstweatherapp.presentation.LocationPresenterImpl;
import ada.osc.myfirstweatherapp.view.adapter.LocationPagerAdapter;
import ada.osc.myfirstweatherapp.view.addLocation.NewLocationActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity implements LocationView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_activity_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.main_activity_navigation_view)
    NavigationView navigationView;

    @BindView(R.id.main_activity_view_pager)
    ViewPager viewPager;

    @BindView(R.id.main_activity_text_view)
    TextView noLocationsTv;

    private LocationPagerAdapter adapter;
    private LocationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        initUI();
        initToolbar();
        initNavigationDrawer();

        presenter = new LocationPresenterImpl(App.getRoomInteractor());
        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAllLocations();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    private void initNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleItemSelectedClick(item.getItemId());
                return false;
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.main_activity_title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        }
    }

    private void handleItemSelectedClick(int itemID) {
        switch (itemID) {
            case R.id.menu_add_new_location: {
                startActivity(new Intent(this, NewLocationActivity.class));
                drawerLayout.closeDrawers();
                break;
            }
        }
    }

    @Override
    public void onLocationAdded(List<Location> locations) {
        if (locations.isEmpty()){
            changeViewPagerVisibility(false);
            changeLocationsTvVisibility(true);
        }else {
            changeViewPagerVisibility(true);
            changeLocationsTvVisibility(false);
            setAdapter(locations);
        }
    }

    private void changeViewPagerVisibility(boolean isVisible) {
        if (isVisible){
            viewPager.setVisibility(View.VISIBLE);
        }else {
            viewPager.setVisibility(View.INVISIBLE);
        }
    }

    private void changeLocationsTvVisibility(boolean isVisible) {
        if (isVisible){
            noLocationsTv.setVisibility(View.VISIBLE);
        }else {
            noLocationsTv.setVisibility(View.INVISIBLE);
        }
    }

    private void setAdapter(List<Location> locations) {
        adapter = new LocationPagerAdapter(getSupportFragmentManager());
        adapter.setAdapterData(locations);
        viewPager.setAdapter(adapter);
    }

}