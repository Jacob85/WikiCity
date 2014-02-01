package il.ac.shenkar.common;

import com.example.WikiCity.R;

import java.io.Serializable;

/**
 * Created by Amsalem on 30/01/14.
 */
public enum cityEnumType implements Serializable
{
//    LOS_ANGELES("Los Angeles", R.drawable.los_angeles,"California"),
    LAS_VEGAS("Las Vegas", R.drawable.las_vegas,"Nevada"),
    MIAMI("Miami", R.drawable.miami, "Florida"),
    NEY_YORK("New York City", R.drawable.new_york, "New York" ),
    CHICAGO ("Chicago", R.drawable.chicago, "Illinois");

    private String cityName;
    private int imageId;
    private String stateName;

    cityEnumType(String cityName, int imageId, String stateName) {
        this.cityName = cityName;
        this.imageId = imageId;
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public int getImageId() {
        return imageId;
    }

    public String getStateName() {
        return stateName;
    }
}

