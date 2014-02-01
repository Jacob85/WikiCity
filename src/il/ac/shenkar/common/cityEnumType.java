package il.ac.shenkar.common;

import com.example.WikiCity.R;

import java.io.Serializable;

/**
 * Created by Amsalem on 30/01/14.
 */
public enum cityEnumType  implements Serializable
{
    LOS_ANGELES("Los Angeles", R.drawable.losangeles,"California"),
  //  LAS_VEGAS("Las Vegas", R.drawable.losangeles,"Nevada"),
    MIAMI("Miami", R.drawable.miami, "Florida"),
    NEY_YORK("New York City", R.drawable.newyork3, "New York" ),
    CHICAGO ("Chicago", R.drawable.chcago, "Illinois"),
    BALTIMORE("Baltimore", R.drawable.baltimore, "Maryland"),
    BOSTON("Boston", R.drawable.boston, "Massachusetts"),
    DALLAS("Dallas ", R.drawable.dallas, "Texas" ),
    DENVER("Denver_City,_Texas", R.drawable.denver, "Texas"),
    HONOLULU("Honolulu", R.drawable.honololo, "Hawaii"),
    HOUSTON("Houston", R.drawable.houston, "Texas"),
    ORLANDO("Orlando", R.drawable.orlando, "Florida"),
    PHOENIX("Phoenix", R.drawable.phonix, "Arizona"),
    SAN_FRANCISCO("San Francisco", R.drawable.sanfransico7, "California"),
    SEATTLE("Seattle", R.drawable.seattle1, "Washington"),
    TULSA("Tulsa", R.drawable.tulsa, "Oklahoma"),
    WASHINGTON("Washington,_D.C.", R.drawable.washington, "Washington");


    private String cityName;
    private int imageId;
    private String stateName;

    cityEnumType (String cityName, int imageId, String stateName) {
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

