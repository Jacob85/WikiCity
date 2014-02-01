package il.ac.GUI;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.WikiCity.R;
import java.util.Calendar;
import java.util.List;

import il.ac.bl.DataAccessObject;
import il.ac.services.DownloadImageAsyncTask;
import il.ac.services.QueryWikipediaCallback;
import il.ac.shenkar.common.CityInfo;
import il.ac.shenkar.common.Logger;
import il.ac.shenkar.common.StateInfo;
import il.ac.shenkar.common.cityEnumType;
import il.ac.shenker.wiki.WikiConsts;
import il.ac.shenker.wiki.WikiImageInfo;

/**
 * Created by hannypeleg on 1/28/14.
 */
public class MainActivity extends Activity {


    private CityInfo cityInfo;
    private Calendar calendar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
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

        mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description for accessibility */
                R.string.drawer_close /* "close drawer" description for accessibility */
        )
        {
            public void onDrawerClosed(View view)
            {
                getActionBar().setTitle("Wiki City");
                invalidateOptionsMenu(); // creates call to

            }

            public void onDrawerOpened(View drawerView)
            {
                getActionBar().setTitle("Wiki City");
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        // set listener to the menu
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);



        Bundle bundle = getIntent().getExtras().getBundle(WikiConsts.CITY_TYPE_BUNDLE);
        cityEnumType type = (cityEnumType) bundle.getSerializable(WikiConsts.CITY_TYPE);
        DataAccessObject.getInstance().getCityInfo(type.getCityName(),new QueryWikipediaCallback<CityInfo>() {
            @Override
            public void done(CityInfo returnedObject, Exception e) {
                if(e == null) {
                    cityInfo = returnedObject;
                    Fragment fragment = new CityFragment(cityInfo);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                } else {
                    Logger.logException(e);
                }
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    private void populateViews () {


        DataAccessObject.getInstance().getImagesUrl(new QueryWikipediaCallback<List<WikiImageInfo>>() {
            @Override
            public void done(List<WikiImageInfo> returnedObject, Exception e)
            {
                if (returnedObject != null)
                {
//                    ImageView imageView = (ImageView) findViewById(R.id.right_twin);
//                    DownloadImageAsyncTask downloadImageAsyncTask = new DownloadImageAsyncTask(imageView);
//                    downloadImageAsyncTask.execute(returnedObject.get(0).getImageUrl());
                }
                else
                {
                    // display error message
                    Logger.logException(e);
                }
            }
        }, cityInfo.getImageSkyLine());



    }

    /**
     * Fragment that appears in the "content_frame".
     */
    public static class CityFragment extends Fragment {

        private TextView cityName, populationNumber, citySite,story_long,state_title,
                yearEstablish, culture,history, storyTitle, areaTitle;
        private View linearLayout;
        private Typeface tfReg,tfLight;
        private CityInfo city;
        private Calendar calendar;
        private StateInfo stateInfo;

        public CityFragment() {
            // Empty constructor required for fragment subclasses
        }
        public CityFragment(CityInfo currCityInfo) {
            if(currCityInfo != null) {
                city = currCityInfo;
                stateInfo = new StateInfo();
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main, container, false);

            calendar = Calendar.getInstance();

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

            linearLayout =  rootView.findViewById(R.id.littles);

            setFonts();
            populateViews();
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

        private void populateViews () {

            if(city.getCityName() != null)
                cityName.setText(city.getCityName().toUpperCase());

            if(city.getCityWebSite() != null)
                citySite.setText(city.getCityWebSite().toString().toUpperCase());

            if(city.getEstablishedDate() != null) {
                calendar.setTime(city.getEstablishedDate());
                yearEstablish.setText((String.valueOf(calendar.get(Calendar.YEAR))));
            }

        }

        public void calculateLittleMen() {
            long statePopulation = stateInfo.getStatePopulation();
            double result = 0;
            int i =0;
            if(city.getTotalPopulation() != 0) {
                result =(city.getTotalPopulation()/statePopulation)*100;
            }

            while (i < result) {

                ImageView image = new ImageView(getActivity().getBaseContext());
                image.setImageResource(R.drawable.man1);
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

                ((LinearLayout) linearLayout).addView(image);

                ++i;
            }

        }
    }
}
