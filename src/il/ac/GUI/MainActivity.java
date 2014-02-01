package il.ac.GUI;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.WikiCity.R;
import java.util.Calendar;
import java.util.List;

import il.ac.bl.DataAccessObject;
import il.ac.services.DownloadImageAsyncTask;
import il.ac.services.QueryWikipediaCallback;
import il.ac.shenkar.common.CityInfo;
import il.ac.shenkar.common.Logger;
import il.ac.shenker.wiki.WikiImageInfo;

/**
 * Created by hannypeleg on 1/28/14.
 */
public class MainActivity extends Activity {


    private CityInfo cityInfo;
    private Calendar calendar;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Movie movie;
    private String[] mPlanetTitles;
    View rootView;
    Typeface font;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mPlanetTitles = getResources().getStringArray(R.array.items);

        View header = getLayoutInflater().inflate(R.layout.header, null);
//        View footer = getLayoutInflater().inflate(R.layout.footer, null);
        mDrawerList.addHeaderView(header);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item));

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

        DataAccessObject.getInstance().getImagesUrl(new QueryWikipediaCallback<List<WikiImageInfo>>() {
            @Override
            public void done(List<WikiImageInfo> returnedObject, Exception e)
            {
                if (returnedObject != null)
                {
                    ImageView imageView = (ImageView) findViewById(R.id.right_twin);
                    DownloadImageAsyncTask downloadImageAsyncTask = new DownloadImageAsyncTask(imageView);
                    downloadImageAsyncTask.execute(returnedObject.get(0).getImageUrl());
                }
                else
                {
                    // display error message
                    Logger.logException(e);
                }
            }
        }, cityInfo.getImageSkyLine());

        Fragment fragment = new CityFragment(cityInfo);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

    }

    /**
     * Fragment that appears in the "content_frame".
     */
    public static class CityFragment extends Fragment {

        private TextView cityName, populationNumber, citySite,story_long,state_title,
                yearEstablish, culture,history, storyTitle, areaTitle;
        private Typeface tfReg,tfLight;
        public CityFragment() {
            // Empty constructor required for fragment subclasses
        }
        public CityFragment(CityInfo cityInfo) {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main, container, false);

            tfReg = Typeface.createFromAsset(getActivity().getAssets(), "fonts/futura_lt_condensed_reg.ttf");
            tfLight = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Futura LT condensd light.ttf");

            cityName = (TextView)rootView.findViewById(R.id.city_name);
            storyTitle = (TextView)rootView.findViewById(R.id.story_text);
            history = (TextView)rootView.findViewById(R.id.history_text);
            culture = (TextView)rootView.findViewById(R.id.culture);
            citySite = (TextView)rootView.findViewById(R.id.site_text);
            areaTitle = (TextView)rootView.findViewById(R.id.area_text);
            yearEstablish = (TextView)rootView.findViewById(R.id.year_title);
            story_long = (TextView)rootView.findViewById(R.id.story_long);
            state_title = (TextView)rootView.findViewById(R.id.state_title);

            setFonts();
            calculateLittleMen();


            return rootView;
        }

        public void setFonts() {

            cityName.setTypeface(tfReg);
            storyTitle.setTypeface(tfReg);
            history.setTypeface(tfReg);
            culture.setTypeface(tfReg);
            citySite.setTypeface(tfReg);
            yearEstablish.setTypeface(tfReg);
            areaTitle.setTypeface(tfReg);
            story_long.setTypeface(tfLight);
            state_title.setTypeface(tfReg);
        }

        public void calculateLittleMen() {

        }
    }
}
