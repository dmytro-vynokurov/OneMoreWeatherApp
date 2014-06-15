package com.dmytro.onemoreweatherapp.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.TextView;
import com.dmytro.onemoreweatherapp.app.model.DailyForecast;
import com.dmytro.onemoreweatherapp.app.model.ForecastStorage;
import com.dmytro.onemoreweatherapp.app.model.RSSParser;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


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

        RSSParser.fetchAndStoreRSS("http://weather.yahooapis.com/forecastrss?w=2502265&u=c", RSSParser.RSSType.FORECAST);

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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
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


            List<DailyForecast> forecasts = ForecastStorage.getInstance().getDailyForecasts();
            if(forecasts!=null){
                DailyForecast forecast = forecasts.get(0);


                TextView city = (TextView)rootView.findViewById(R.id.city);
                TextView date = (TextView)rootView.findViewById(R.id.date);
                TextView dayOfWeek = (TextView)rootView.findViewById(R.id.dayOfWeek);
                TextView temp = (TextView)rootView.findViewById(R.id.currentTemp);
                TextView humidity = (TextView)rootView.findViewById(R.id.currentHumidity);
                TextView pressure = (TextView)rootView.findViewById(R.id.currentPressure);
                TextView wind = (TextView)rootView.findViewById(R.id.currentWind);
                TextView description = (TextView)rootView.findViewById(R.id.currentDescription);

                city.setText(forecast.getCity().toString());
                date.setText(new SimpleDateFormat("MMM").format(forecast.getDate().getTime())+
                        forecast.getDate().get(Calendar.DAY_OF_MONTH));
                dayOfWeek.setText(new SimpleDateFormat("EEEE").format(forecast.getDate().getTime()));
                temp.setText(forecast.getCurrentTemperature().toString());
                humidity.setText("humidity: "+String.valueOf(forecast.getHumidity())+"%");
                pressure.setText("pressure: "+String.valueOf(forecast.getPressure())+"mm hg");
                wind.setText("wind: "+forecast.getWind().toString());
                description.setText(forecast.getCurrentDescription());
            }

            return rootView;
        }
    }

}
