package il.ac.GUI;

import android.app.*;
import android.content.DialogInterface;
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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import com.example.WikiCity.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import il.ac.bl.DataAccessObject;
import il.ac.services.DownloadImageAsyncTask;
import il.ac.services.QueryWikipediaCallback;
import il.ac.shenkar.common.*;
import il.ac.shenker.wiki.PageSection;
import il.ac.shenker.wiki.WikiConsts;
import il.ac.shenker.wiki.WikiImageInfo;

/**
 * Created by hannypeleg on 1/28/14.
 */
public class MainActivity extends Activity {


    private CityInfo cityInfo;
    private cityEnumType type;
    private Calendar calendar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private Movie movie;
    private String[] mPlanetTitles;
    private ArrayList<PageSection> allPageSections;
    View rootView;
    Typeface font;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DialogHelper.showProgressDialog("Fetching Data From Web ...", this);
        mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mPlanetTitles = getResources().getStringArray(R.array.items);

        View header = getLayoutInflater().inflate(R.layout.header, null);
//        View footer = getLayoutInflater().inflate(R.layout.footer, null);
        mDrawerList.addHeaderView(header);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item));

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (position == 0)
                {
                    // do nothing - the header is pressed
                    return;
                }
                DialogHelper.showProgressDialog("Loading...", MainActivity.this);
                DataAccessObject.getInstance().getWikiSectionInfo(allPageSections.get(position - 1), new QueryWikipediaCallback<PageSection>() {
                    @Override
                    public void done(PageSection returnedObject, Exception e)
                    {
                        if (returnedObject != null)
                        {
                            FragmentManager fm = getFragmentManager();
                            DialogFragment dialog = new WebViewFragment(MainActivity.this, returnedObject); // creating new object
                            dialog.show(fm, "dialog");
                        }
                        DialogHelper.closeProggresDialog();
                    }
                } );


            }
        });


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
        type = (cityEnumType) bundle.getSerializable(WikiConsts.CITY_TYPE);
        DataAccessObject.getInstance().getCityInfo(type.getCityName(),new QueryWikipediaCallback<CityInfo>() {
            @Override
            public void done(CityInfo returnedObject, Exception e) {
                if(e == null) {
                    cityInfo = returnedObject;
                    populateViews();
                    DialogHelper.closeProggresDialog();
                    Fragment fragment = new CityFragment(cityInfo,type);
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
    private void populateViews ()
    {

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, createPageSectionsArray()));

        /*DataAccessObject.getInstance().getImagesUrl(new QueryWikipediaCallback<List<WikiImageInfo>>() {
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
*/
    }

    private String[] createPageSectionsArray()
    {
        ArrayList<String> pageSectionsToReturn = new ArrayList<String>();
        allPageSections = new ArrayList<PageSection>();
        ArrayList<PageSection> inputList = (ArrayList<PageSection>) cityInfo.getPageSections();

        for(PageSection currInpPageSection: inputList)
        {
            StringBuilder stringBuilder = new StringBuilder();

            buildPAgeSectionLine(currInpPageSection, stringBuilder);
            pageSectionsToReturn.add(stringBuilder.toString());
            allPageSections.add(currInpPageSection);
            if (currInpPageSection.getSubSections() == null)
            {
               continue;
            }
            else
            {
                ArrayList<PageSection> subList = currInpPageSection.getSubSections();
                for (PageSection currSub : subList)
                {
                    StringBuilder subBuilder = new StringBuilder();
                    buildPAgeSectionLine(currSub, subBuilder);
                    stringBuilder.append(" ");
                    pageSectionsToReturn.add(subBuilder.toString());
                    allPageSections.add(currSub);
                    if (currSub.getSubSections() == null)
                    {
                       continue;
                    }
                    else
                    {
                        ArrayList<PageSection> subSubList = currSub.getSubSections();
                        for (PageSection currSubSub : subSubList)
                        {
                            StringBuilder subSubBuilder = new StringBuilder();
                            subSubBuilder.append(" ");
                            buildPAgeSectionLine(currSubSub, subSubBuilder);
                            pageSectionsToReturn.add(subSubBuilder.toString());
                            allPageSections.add(currSubSub);
                        }
                    }
                }
            }


        }
        mPlanetTitles = new String[pageSectionsToReturn.size()];
        return pageSectionsToReturn.toArray(mPlanetTitles);
    }

    private void buildPAgeSectionLine(PageSection currInpPageSection, StringBuilder stringBuilder) {
       /* stringBuilder.append(currInpPageSection.getSectionNumber());
        stringBuilder.append(" ");*/
        stringBuilder.append(currInpPageSection.getSectionName());
    }

    /**
     * Fragment that appears in the "content_frame".
     */
    public static class CityFragment extends Fragment {

        private TextView cityName, populationNumber, citySite,story_long, state_title, water_value,
                min_tmp, max_tmp, yearEstablish, culture,history, storyTitle, areaTitle;
        private View linearLayout;
        private Typeface tfReg,tfLight;
        private CityInfo city;
        private Calendar calendar;
        private StateInfo stateInfo;
        private cityEnumType type;

        public CityFragment() {
            // Empty constructor required for fragment subclasses
        }
        public CityFragment(CityInfo currCityInfo, cityEnumType currType) {
            if(currCityInfo != null) {
                city = currCityInfo;
                type = currType;
                stateInfo = new StateInfo();
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main, container, false);

            calendar = Calendar.getInstance();

            cityName = (TextView)rootView.findViewById(R.id.city_name);
            storyTitle = (TextView)rootView.findViewById(R.id.story_text);
//            history = (TextView)rootView.findViewById(R.id.history_text);
//            culture = (TextView)rootView.findViewById(R.id.culture);
            citySite = (TextView)rootView.findViewById(R.id.site_text);
            areaTitle = (TextView)rootView.findViewById(R.id.area_text);
            yearEstablish = (TextView)rootView.findViewById(R.id.year_title);
            story_long = (TextView)rootView.findViewById(R.id.story_long);
            state_title = (TextView)rootView.findViewById(R.id.state_title);
            water_value = (TextView)rootView.findViewById(R.id.water_val);
            min_tmp = (TextView)rootView.findViewById(R.id.min_tmp);
            max_tmp = (TextView)rootView.findViewById(R.id.max_tmp);

            linearLayout =  rootView.findViewById(R.id.littles);
            setTypeface("futura_lt_condensed_reg.ttf");
            populateViews();
            calculateLittleMen();


            return rootView;
        }

        public void setTypeface(String regular) {
            tfReg = Typeface.createFromAsset(getActivity().getAssets(), "fonts/"+regular);
           // tfLight = Typeface.createFromAsset(getActivity().getAssets(), light);
            setFonts(tfReg);
        }

        public void setFonts(Typeface tfReg) {

            cityName.setTypeface(tfReg);
            storyTitle.setTypeface(tfReg);
            citySite.setTypeface(tfReg);
            yearEstablish.setTypeface(tfReg);
            areaTitle.setTypeface(tfReg);
            story_long.setTypeface(tfReg);
            state_title.setTypeface(tfReg);
            water_value.setTypeface(tfReg);
            min_tmp.setTypeface(tfReg);
            max_tmp.setTypeface(tfReg);

        }

        private void populateViews () {

            if(city.getCityName() != null)
                cityName.setText(city.getCityName().toUpperCase());

            if(city.getCityWebSite() != null) {
                citySite.setText(city.getCityWebSite().toString().toUpperCase());
                citySite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogHelper.showProgressDialog("Loading...", getActivity());
                        FragmentManager fm = getFragmentManager();
                        DialogFragment dialog = new WebViewFragment(getActivity(),city.getCityWebSite()); // creating new object
                        dialog.show(fm, "dialog");
                        DialogHelper.closeProggresDialog();
                    }
                });
            }

            if(city.getEstablishedDate() != null) {
                calendar.setTime(city.getEstablishedDate());
                yearEstablish.setText((String.valueOf(calendar.get(Calendar.YEAR))));
            }

            if(city.getRegion() != null)
                state_title.setText(city.getRegion().toString().toUpperCase());

            if(city.getCityGeneralInfo() != null)
                story_long.setText(city.getCityGeneralInfo().toString());

            if(city.getWaterAreaPercentage() != 0)
                water_value.setText(String.valueOf(city.getWaterAreaPercentage())+ "%");

            if(city.getYearMinTemp()!= 0)
                min_tmp.setText(String.valueOf(city.getYearMinTemp())+ "˚F");

            if(city.getYearMaxTemp()!= 0)
                max_tmp.setText(String.valueOf(city.getYearMaxTemp())+ "˚F");
        }

        public void calculateLittleMen() {
            double statePopulation = type.getTotalStatePopulation();
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
