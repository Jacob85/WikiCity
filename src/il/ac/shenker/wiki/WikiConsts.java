package il.ac.shenker.wiki;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/26/13
 * Time: 10:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class WikiConsts
{
    public static String WIKI_API_END_POINT = "http://en.wikipedia.org/w/api.php";
    public static String SPACE = "%20";


    //City keys
    public static String CITY_POPULATION ="population_total";
    public static String TIME_ZONE_OFFSET = "utc_offset";
    public static String LEADER_TITLE = "leader_title";
    public static String LEADER_NAME = "leader_name";
    public static String ESTABLISH_DATE ="established_date";
    public static String CITY_LAT_D = "latd";        //34. N
    public static String CITY_LAT_M = "latm";        // 0.3 N         CITY_LAT_D.CITY_LAT_M ==> 34.03 N
    public static String CITY_LONG_D = "longd";
    public static String CITY_LONG_M = "longm";
}
