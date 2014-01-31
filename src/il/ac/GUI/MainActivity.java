package il.ac.GUI;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.WikiCity.R;
import java.util.Calendar;

import il.ac.bl.DataAccessObject;
import il.ac.services.QueryWikipediaCallback;
import il.ac.shenkar.common.CityInfo;
import il.ac.shenkar.common.Logger;

/**
 * Created by hannypeleg on 1/28/14.
 */
public class MainActivity extends Activity {

    private TextView cityName, populationNumber, citySite, yearEstablish, timeZone;
    private CityInfo cityInfo;
    private Calendar calendar;
    private DrawerLayout mDrawerLayout;
    private Movie movie;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        cityName= (TextView)findViewById(R.id.city_name);
//        populationNumber = (TextView)findViewById(R.id.population_number);
//        citySite = (TextView) findViewById(R.id.site_text);
//        yearEstablish = (TextView) findViewById(R.id.sub_title);
//        timeZone = (TextView) findViewById(R.id.time_zone);
        mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);

        DataAccessObject.getInstance().getCityInfo("Los Angeles",new QueryWikipediaCallback<CityInfo>() {
            @Override
            public void done(CityInfo returnedObject, Exception e) {
                if(e == null) {
                    calendar = Calendar.getInstance();
                    cityInfo = returnedObject;
                    populateViews();
                } else {
                    Logger.logException(e);
                }
            }
        });

    }

    private void populateViews () {
//        if(cityInfo.getCityName() != null)
//            cityName.setText(cityInfo.getCityName());
//
//        if(cityInfo.getTotalPopulation() != 0)
//            populationNumber.setText(String.valueOf(cityInfo.getTotalPopulation()));
//
//        if(cityInfo.getCityWebSite() != null)
//            citySite.setText(cityInfo.getCityWebSite().toString());
//
//        if(cityInfo.getEstablishedDate() != null) {
//            calendar.setTime(cityInfo.getEstablishedDate());
//            yearEstablish.setText((String.valueOf(calendar.get(Calendar.YEAR))));
//        }
//
//        if(cityInfo.getUtcOffset() != 0) {
//            timeZone.setText("GMT " + String.valueOf(cityInfo.getUtcOffset()));
//        }
        Fragment fragment = new PlanetFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PlanetFragment extends Fragment {

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main, container, false);

            return rootView;
        }
    }
}
