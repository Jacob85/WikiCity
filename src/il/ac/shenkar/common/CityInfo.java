package il.ac.shenkar.common;

import com.google.android.gms.maps.model.LatLng;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Amsalem on 16/01/14.
 */
public class CityInfo
{
    private String cityName;
    private String nickName;
    private String region;           //CA-US
    private String yearMinTemp;
    private String imageSealWikiName;
    private String imageFlagWikiName;
    private String imageMapWikiName;
    private Date establishedDate;
    private String countryName;
    private URL imageSkyLineUrl;
    private URL cityWebSite;
    private URL wikiOrigPageUrl;
    private double waterAreaPercentage;
    private int utcOffset;
    private int wikiRevisionId;
    private int wikiPageId;
    private long totalPopulation;
    private int postalCode;
    private int numberOfRainDaysAyear;
    private List<WikiPageSection> pageSections;
    private List<URL> wikiExternalLinksList;
    private LatLng geoLocation;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getYearMinTemp() {
        return yearMinTemp;
    }

    public void setYearMinTemp(String yearMinTemp) {
        this.yearMinTemp = yearMinTemp;
    }

    public String getImageSealWikiName() {
        return imageSealWikiName;
    }

    public void setImageSealWikiName(String imageSealWikiName) {
        this.imageSealWikiName = imageSealWikiName;
    }

    public String getImageFlagWikiName() {
        return imageFlagWikiName;
    }

    public void setImageFlagWikiName(String imageFlagWikiName) {
        this.imageFlagWikiName = imageFlagWikiName;
    }

    public String getImageMapWikiName() {
        return imageMapWikiName;
    }

    public void setImageMapWikiName(String imageMapWikiName) {
        this.imageMapWikiName = imageMapWikiName;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public URL getImageSkyLineUrl() {
        return imageSkyLineUrl;
    }

    public void setImageSkyLineUrl(URL imageSkyLineUrl) {
        this.imageSkyLineUrl = imageSkyLineUrl;
    }

    public URL getCityWebSite() {
        return cityWebSite;
    }

    public void setCityWebSite(URL cityWebSite) {
        this.cityWebSite = cityWebSite;
    }

    public URL getWikiOrigPageUrl() {
        return wikiOrigPageUrl;
    }

    public void setWikiOrigPageUrl(URL wikiOrigPageUrl) {
        this.wikiOrigPageUrl = wikiOrigPageUrl;
    }

    public double getWaterAreaPercentage() {
        return waterAreaPercentage;
    }

    public void setWaterAreaPercentage(double waterAreaPercentage) {
        this.waterAreaPercentage = waterAreaPercentage;
    }

    public int getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    public int getWikiRevisionId() {
        return wikiRevisionId;
    }

    public void setWikiRevisionId(int wikiRevisionId) {
        this.wikiRevisionId = wikiRevisionId;
    }

    public int getWikiPageId() {
        return wikiPageId;
    }

    public void setWikiPageId(int wikiPageId) {
        this.wikiPageId = wikiPageId;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getNumberOfRainDaysAyear() {
        return numberOfRainDaysAyear;
    }

    public void setNumberOfRainDaysAyear(int numberOfRainDaysAyear) {
        this.numberOfRainDaysAyear = numberOfRainDaysAyear;
    }

    public List<WikiPageSection> getPageSections() {
        return pageSections;
    }

    public void setPageSections(List<WikiPageSection> pageSections) {
        this.pageSections = pageSections;
    }

    public List<URL> getWikiExternalLinksList() {
        return wikiExternalLinksList;
    }

    public void setWikiExternalLinksList(List<URL> wikiExternalLinksList) {
        this.wikiExternalLinksList = wikiExternalLinksList;
    }

    public LatLng getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(LatLng geoLocation) {
        this.geoLocation = geoLocation;
    }

    public static class CityInfoBuilder
    {
        private String cityName;
        private String nickName;
        private String region;           //CA-US
        private String yearMinTemp;
        private String imageSealWikiName;
        private String imageFlagWikiName;
        private String imageMapWikiName;
        private Date establishedDate;
        private String countryName;
        private URL imageSkyLineUrl;
        private URL cityWebSite;
        private URL wikiOrigPageUrl;
        private double waterAreaPercentage;
        private int utcOffset;
        private int wikiRevisionId;
        private int wikiPageId;
        private long totalPopulation;
        private int postalCode;
        private int numberOfRainDaysAyear;
        private List<WikiPageSection> pageSections;
        private List<URL> wikiExternalLinksList;
        private LatLng geoLocation;

        public CityInfoBuilder() {};

        public CityInfoBuilder cityName(String name)
        {
            this.cityName = name;
            return this;
        }
        public CityInfoBuilder nickName(String nickName)
        {
            this.nickName = nickName;
            return this;
        }
        public CityInfoBuilder region (String region)
        {
            this.region = region;
            return this;
        }
        public CityInfoBuilder yearMinTemp (String yearMinTemp)
        {
            this.yearMinTemp = yearMinTemp;
            return this;
        }
        public CityInfoBuilder imageSealName(String imageSealWikiName)
        {
            this.imageSealWikiName = imageSealWikiName;
            return this;
        }
        public CityInfoBuilder imageFlagName(String imageSealWikiName)
        {
            this.imageSealWikiName = imageSealWikiName;
            return this;
        }
        public CityInfoBuilder imageMapName (String imageMapWikiName)
        {
            this.imageMapWikiName = imageMapWikiName;
            return this;
        }
        public CityInfoBuilder establishedDate(String dateString)
        {

            try
            {
                this.establishedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            } catch (ParseException e)
            {
                Logger.logException(e);
                this.establishedDate = new Date();
            }
            return this;
        }
        public CityInfoBuilder countryName(String countryName)
        {
            this.countryName = countryName;
            return this;
        }
        public CityInfoBuilder imageSkyUrl(String url)
        {
            try
            {
                this.imageSkyLineUrl = new URL(url);
            } catch (MalformedURLException e)
            {
                Logger.logException(e);
                this.imageSkyLineUrl = null;
            }
            return this;
        }
        public CityInfoBuilder cityWebSite (String url)
        {
            try
            {
                this.cityWebSite = new URL(url);
            } catch (MalformedURLException e)
            {
              Logger.logException(e);
                this.cityWebSite = null;
            }
            return this;
        }
    }




}
