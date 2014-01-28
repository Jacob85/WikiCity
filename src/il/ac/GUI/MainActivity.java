package il.ac.GUI;

import android.app.Activity;
import android.os.Bundle;
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

    private TextView cityName, populationNumber, citySite, yearEstablish;
    private CityInfo cityInfo;
    private Calendar calendar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        cityName= (TextView)findViewById(R.id.city_name);
        populationNumber = (TextView)findViewById(R.id.population_number);
        citySite = (TextView) findViewById(R.id.site_text);
        yearEstablish = (TextView) findViewById(R.id.sub_title);

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
        if(cityInfo.getCityName() != null)
            cityName.setText(cityInfo.getCityName());

        if(cityInfo.getTotalPopulation() != 0)
            populationNumber.setText(String.valueOf(cityInfo.getTotalPopulation()));

        if(cityInfo.getCityWebSite() != null)
            citySite.setText(cityInfo.getCityWebSite().toString());

        if(cityInfo.getEstablishedDate() != null) {
            calendar.setTime(cityInfo.getEstablishedDate());
            yearEstablish.setText(calendar.get(Calendar.YEAR));
        }
    }
}