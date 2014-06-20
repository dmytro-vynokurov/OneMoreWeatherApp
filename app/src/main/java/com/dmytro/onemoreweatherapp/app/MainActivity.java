package com.dmytro.onemoreweatherapp.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.TextView;
import com.dmytro.onemoreweatherapp.app.model.City;
import com.dmytro.onemoreweatherapp.app.model.Forecast;
import com.dmytro.onemoreweatherapp.app.yahoo.api.Storage;
import com.dmytro.onemoreweatherapp.app.yahoo.api.YahooApi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Storage storage = Storage.getInstance();
        Map<City,Forecast> forecasts = storage.getForecasts();
        forecasts.put(new City(924938,"Kyiv",0),null);
        forecasts.put(new City(502075,"Krakow",1),null);
        forecasts.put(new City(2502265,"Sunnyvale",2), null);

        YahooApi.updateAllForecasts(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position,MainActivity.this);
        }

        @Override
        public int getCount() {
//            notifyDataSetChanged();
//            return Storage.getInstance().numberOfCities();
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            try{
                return Storage.getInstance().getCitiesList().get(position).getName();
            }catch(NullPointerException npe){
                return null;
            }
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private int currentFragmentId;
        private Activity activity;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, Activity activity) {
            return new PlaceholderFragment(sectionNumber,activity);
        }

        public PlaceholderFragment(int currentFragmentId,Activity activity) {
            this.currentFragmentId = currentFragmentId;
            this.activity=activity;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            // Look up the AdView as a resource and load a request.
            AdView adView = (AdView)rootView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("7BD0CC159298FEEE10BBDCF2A26E9B7D").build();
            adView.loadAd(adRequest);

            YahooApi.updateAllForecasts(activity);
            updateForecastOnView(rootView);

            return rootView;
        }

        private void updateForecastOnView(View rootView) {
            List<Forecast> forecasts = Storage.getInstance().getForecastValues();
            if(!forecasts.isEmpty() && forecasts.get(currentFragmentId)!=null){
                Forecast forecast = forecasts.get(currentFragmentId);


                TextView city = (TextView)rootView.findViewById(R.id.city);
                TextView date = (TextView)rootView.findViewById(R.id.date);
                TextView dayOfWeek = (TextView)rootView.findViewById(R.id.dayOfWeek);
                TextView temp = (TextView)rootView.findViewById(R.id.currentTemp);
                TextView humidity = (TextView)rootView.findViewById(R.id.currentHumidity);
                TextView pressure = (TextView)rootView.findViewById(R.id.currentPressure);
                TextView wind = (TextView)rootView.findViewById(R.id.currentWind);
                TextView description = (TextView)rootView.findViewById(R.id.currentDescription);

                city.setText(forecast.getCity().toString());
                date.setText(new SimpleDateFormat("MMMM").format(forecast.getDate().getTime())+" "+
                        forecast.getDate().get(Calendar.DAY_OF_MONTH));
                dayOfWeek.setText(new SimpleDateFormat("EEEE").format(forecast.getDate().getTime()));
                temp.setText(forecast.getCurrentTemperature().toString());
                humidity.setText("humidity: "+String.valueOf(forecast.getHumidity())+"%");
                pressure.setText("pressure: "+String.valueOf(forecast.getPressure())+"mm hg");
                wind.setText(forecast.getWind().toString());
                description.setText(forecast.getCurrentDescription());
            }
        }
    }

}
