package il.ac.shenkar.common;

import com.example.WikiCity.R;

import java.io.Serializable;

/**
 * Created by Amsalem on 30/01/14.
 */
public enum cityEnumType  implements Serializable
{
    LOS_ANGELES("Los Angeles", R.drawable.losangeles,"California", 38040000, "fonts/Yes Please - DeliciousPro.oft"),
  //  LAS_VEGAS("Las Vegas", R.drawable.losangeles,"Nevada"),
    MIAMI("Miami", R.drawable.miami, "Florida", 19552860, "fonts/futura LT condensd.ttf"),
    NEY_YORK("New York City", R.drawable.newyork3, "New York" , 19651127, "fonts/ENGLISHT.TTF" ),
    CHICAGO ("Chicago", R.drawable.chcago, "Illinois", 12882135, "fonts/Rex Bold Inline.oft"),
    BALTIMORE("Baltimore", R.drawable.baltimore, "Maryland", 5928814, "fonts/American Type writer LT cn.ttf" ),
    BOSTON("Boston", R.drawable.boston, "Massachusetts", 6692824, "fonts/futura LT condensd.ttf" ),
    DALLAS("Dallas ", R.drawable.dallas, "Texas", 26448193, "fonts/Weston Free.otf"  ),
    DENVER("Denver_City,_Texas", R.drawable.denver, "Texas", 26448193, "fonts/Weston Free.otf"),
    HONOLULU("Honolulu", R.drawable.honololo, "Hawaii", 1404054, "fonts/futura LT condensd.ttf" ),
    HOUSTON("Houston", R.drawable.houston, "Texas", 26448193, "fonts/Weston Free.otf" ),
    ORLANDO("Orlando,_Florida", R.drawable.orlando, "Florida", 19552860, "fonts/futura LT condensd.ttf"),
    PHOENIX("Phoenix", R.drawable.phonix, "Arizona", 6626624, "fonts/BOOKOSB.TTF" ),
    SAN_FRANCISCO("San Francisco", R.drawable.sanfransico7, "California", 38040000,"fonts/Yes Please - DeliciousPro.oft" ),
    SEATTLE("Seattle", R.drawable.seattle1, "Washington", 6971406, "fonts/Kankin.otf" ),
    TULSA("Tulsa,_Oklahoma", R.drawable.tulsa, "Oklahoma", 3850568, "fonts/Kankin.otf" ),
    WASHINGTON("Washington,_D.C.", R.drawable.washington, "Washington", 6971406, "fonts/Kankin.otf"),
    OKLAHOMA("Oklahoma_City", R.drawable.oklahoma, "Oklahoma", 3850568,"fonts/Kankin.otf" );



    private String cityName;
    private int imageId;
    private String stateName;
    private int totalStatePopulation;
    private String pathToFont;

    cityEnumType (String cityName, int imageId, String stateName, int totalStatePopulation, String pathToFont) {
        this.cityName = cityName;
        this.imageId = imageId;
        this.stateName = stateName;
        this.totalStatePopulation = totalStatePopulation;
        this.pathToFont = pathToFont;
    }

    public int getTotalStatePopulation() {
        return totalStatePopulation;
    }

    public String getPathToFont() {
        return pathToFont;
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

