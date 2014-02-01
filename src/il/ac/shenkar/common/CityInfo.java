package il.ac.shenkar.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
import il.ac.shenker.wiki.PageSection;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Amsalem on 16/01/14.
 */
public class CityInfo
{
    private String cityName;
    private String nickName;
    private String region;           //CA-US
    private double yearMinTemp;
    private double yearMaxTemp;
    private String imageSealWikiName;
    private String imageFlagWikiName;
    private String imageMapWikiName;
    private String cityGeneralInfo;
    private Date establishedDate;
    private URL countryNameUrl;
    private String imageSkyLine;
    private URL cityWebSite;
    private URL wikiOrigPageUrl;
    private double waterAreaPercentage;
    private double totalAreaKm;
    private double totalLandAreaKm;
    private int utcOffset;
    private int wikiRevisionId;
    private int wikiPageId;
    private long totalPopulation;
    private int postalCode;
    private double numberOfRainDaysAyear;
    private List<PageSection> pageSections;
    private List<URL> wikiExternalLinksList;
    private LatLng geoLocation;

    private CityInfo(CityInfoBuilder builder)
    {
        this.cityName = builder.cityName;
        this.nickName = builder.nickName;
        this.region = builder.region;
        this.yearMaxTemp = builder.yearMaxTemp;
        this.yearMinTemp = builder.yearMinTemp;
        this.imageFlagWikiName = builder.imageFlagWikiName;
        this.imageMapWikiName = builder.imageMapWikiName;
        this.imageSealWikiName = builder.imageSealWikiName;
        this.establishedDate = builder.establishedDate;
        this.countryNameUrl = builder.countryNameUrl;
        this.imageSkyLine = builder.imageSkyLine;
        this.cityWebSite = builder.cityWebSite;
        this.wikiOrigPageUrl = builder.wikiOrigPageUrl;
        this.waterAreaPercentage = builder.waterAreaPercentage;
        this.utcOffset = builder.utcOffset;
        this.wikiRevisionId = builder.wikiRevisionId;
        this.wikiPageId = builder.wikiPageId;
        this.totalPopulation = builder.totalPopulation;
        this.postalCode = builder.postalCode;
        this.numberOfRainDaysAyear = builder.numberOfRainDaysAyear;
        this.pageSections = builder.pageSections;
        this.wikiExternalLinksList = builder.wikiExternalLinksList;
        this.geoLocation = builder.geoLocation;
        this.cityGeneralInfo = builder.cityGeneralInfo;
        this.totalAreaKm = builder.totalAreaKm;
        this.totalLandAreaKm = builder.totalLandAreaKm;

    }


    public void setYearMinTemp(double yearMinTemp) {
        this.yearMinTemp = yearMinTemp;
    }

    public void setYearMaxTemp(double yearMaxTemp) {
        this.yearMaxTemp = yearMaxTemp;
    }

    public String getCityGeneralInfo() {
        return cityGeneralInfo;
    }

    public void setCityGeneralInfo(String cityGeneralInfo) {
        this.cityGeneralInfo = cityGeneralInfo;
    }

    public void setNumberOfRainDaysAyear(double numberOfRainDaysAyear) {
        this.numberOfRainDaysAyear = numberOfRainDaysAyear;
    }

    public double getYearMaxTemp() {
        return yearMaxTemp;
    }

    public void setYearMaxTemp(int yearMaxTemp) {
        this.yearMaxTemp = yearMaxTemp;
    }

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

    public double getYearMinTemp() {
        return yearMinTemp;
    }

    public void setYearMinTemp(int yearMinTemp) {
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

    public URL getCountryNameUrl() {
        return countryNameUrl;
    }

    public void setCountryNameUrl(URL countryNameUrl) {
        this.countryNameUrl = countryNameUrl;
    }

    public String getImageSkyLine() {
        return imageSkyLine;
    }

    public void setImageSkyLine(String imageSkyLine) {
        this.imageSkyLine = imageSkyLine;
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

    public double getNumberOfRainDaysAyear() {
        return numberOfRainDaysAyear;
    }

    public void setNumberOfRainDaysAyear(int numberOfRainDaysAyear) {
        this.numberOfRainDaysAyear = numberOfRainDaysAyear;
    }

    public List<PageSection> getPageSections() {
        return pageSections;
    }

    public void setPageSections(List<PageSection> pageSections) {
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
        private double yearMinTemp;
        private double yearMaxTemp;
        private String imageSealWikiName;
        private String imageFlagWikiName;
        private String imageMapWikiName;
        private String cityGeneralInfo;
        private Date establishedDate;
        private URL countryNameUrl;
        private String imageSkyLine;
        private URL cityWebSite;
        private URL wikiOrigPageUrl;
        private double waterAreaPercentage;
        private double totalAreaKm;
        private double totalLandAreaKm;
        private int utcOffset;
        private int wikiRevisionId;
        private int wikiPageId;
        private long totalPopulation;
        private int postalCode;
        private double numberOfRainDaysAyear;
        private List<PageSection> pageSections;
        private List<URL> wikiExternalLinksList;
        private LatLng geoLocation;

        public CityInfoBuilder() {};

        public CityInfoBuilder totalAreaKm (double totalAreaKm)
        {
            this.totalAreaKm = totalAreaKm;
            return this;
        }
        public CityInfoBuilder totalLandAreaKm(double totalLandAreaKm)
        {
            this.totalLandAreaKm = totalLandAreaKm;
            return this;
        }
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
        public CityInfoBuilder cityGeneralInfo(String cityGeneralInfo)
        {
            this.cityGeneralInfo = cityGeneralInfo;
            return this;
        }
        public CityInfoBuilder yearMinTemp (double yearMinTemp)
        {
            this.yearMinTemp = yearMinTemp;
            return this;
        }
        public CityInfoBuilder yearMaxTemp (double yearMaxTemp)
        {
            this.yearMaxTemp = yearMaxTemp;
            return this;
        }
        public CityInfoBuilder imageSealName(String imageSealWikiName)
        {
            this.imageSealWikiName = imageSealWikiName;
            return this;
        }
        public CityInfoBuilder imageFlagName(String imageSealWikiName)
        {
            this.imageFlagWikiName = imageSealWikiName;
            return this;
        }
        public CityInfoBuilder imageMapName (String imageMapWikiName)
        {
            this.imageMapWikiName = imageMapWikiName;
            return this;
        }
        public CityInfoBuilder establishedDate(String dateString)
        {

            if (dateString == null || dateString.isEmpty() )
            {
                this.establishedDate = null;
                return this;
            }
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
            if (countryName == null)
            {
                this.countryNameUrl = null;
                return this;
            }
            try
            {
                this.countryNameUrl = new URL(countryName);
            } catch (MalformedURLException e)
            {
                Logger.logException(e);
                this.countryNameUrl = null;
            }
            return this;
        }
        public CityInfoBuilder imageSky(String name)
        {
           this.imageSkyLine = name;
            return this;
        }
        public CityInfoBuilder cityWebSite (String url)
        {
            if (url == null)
            {
                this.cityWebSite = null;
                return this;
            }
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
        public CityInfoBuilder wikiOrigPageUrl(String url)
        {
            try
            {
                this.wikiOrigPageUrl = new URL(url);
            } catch (MalformedURLException e)
            {
                Logger.logException(e);
                this.wikiOrigPageUrl = null;
            }
            return this;
        }
        public CityInfoBuilder waterAreaPercentage (double waterAreaPercentage)
        {
            this.waterAreaPercentage = waterAreaPercentage;
            return this;
        }
        public CityInfoBuilder utcOffset (int utcOffset)
        {
            this.utcOffset = utcOffset;
            return this;
        }
        public CityInfoBuilder wikiRevisionId(int wikiRevisionId)
        {
            this.wikiRevisionId = wikiRevisionId;
            return this;
        }
        public CityInfoBuilder wikipageId(int wikiPageId)
        {
            this.wikiPageId = wikiPageId;
            return this;
        }
        public CityInfoBuilder totalPopulation (long totalPopulation)
        {
            this.totalPopulation = totalPopulation;
            return this;
        }
        public CityInfoBuilder postalCode (int postalCode)
        {
            this.postalCode = postalCode;
            return this;
        }
        public CityInfoBuilder numberofRainDaysAyear(double numberOfRainDaysAyear)
        {
            this.numberOfRainDaysAyear = numberOfRainDaysAyear;
            return this;
        }
        public CityInfoBuilder addPageSection (PageSection section)
        {
            if (this.pageSections == null)
                this.pageSections = new ArrayList<PageSection>();
            this.pageSections.add(section);
            return this;
        }
        public CityInfoBuilder PageSections (List<PageSection> pageSections)
        {
            this.pageSections = pageSections;
            return this;
        }
        public CityInfoBuilder addExternalLink (URL externalLink)
        {
            if (this.wikiExternalLinksList == null)
                this.wikiExternalLinksList = new ArrayList<URL>();
            this.wikiExternalLinksList.add(externalLink);
            return this;
        }
        public CityInfoBuilder geoLocation (String latString, String lngString)
        {
            this.geoLocation = new LatLng(Double.parseDouble(latString), Double.parseDouble(lngString));
            return this;
        }
        public CityInfo build()
        {
            return new CityInfo(this);
        }
    }




}
