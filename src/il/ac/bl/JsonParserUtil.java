package il.ac.bl;

import android.util.Log;
import android.widget.ArrayAdapter;
import il.ac.shenkar.common.CityInfo;
import il.ac.shenkar.common.Logger;
import il.ac.shenkar.common.WikiPageSection;
import il.ac.shenker.wiki.PageSection;
import il.ac.shenker.wiki.WikiConsts;
import il.ac.shenker.wiki.WikiImageInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/26/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonParserUtil
{
    // private C'tor to
    private JsonParserUtil()
    {
    }

    public static Collection<WikiImageInfo> parseJsonToImagesList(JSONObject jsonObject) throws JSONException
    {
        if (jsonObject == null)
            return null;
        Collection<WikiImageInfo> collectionToReturn = new ArrayList<WikiImageInfo>();

        JSONObject query = jsonObject.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");
        Iterator<?> keys = pages.keys();
        WikiImageInfo.ImageInfoBuilder builder;
        while (keys.hasNext())
        {
            builder = new WikiImageInfo.ImageInfoBuilder();
            String key = (String) keys.next();
            JSONObject pageId = pages.getJSONObject(key);
            builder.Title(pageId.getString("title"));

            JSONArray imageInfo = pageId.getJSONArray("imageinfo");
            JSONObject rev0 = (JSONObject)imageInfo.get(0);
            builder.Url(rev0.getString("url"));
            collectionToReturn.add(builder.build());
        }

        return collectionToReturn;
    }

    public static List<PageSection> parseWikiPageSections(JSONObject json)
    {
        if (json == null)
            return null;
        List<PageSection> pageSections = new ArrayList<PageSection>();
        try
        {
            JSONObject parse = json.getJSONObject("parse");
            JSONArray sections = parse.getJSONArray("sections");
            JSONObject currSection;
            for (int i=0; i< sections.length(); i++)
            {
                currSection = sections.getJSONObject(i);
                PageSection pageSection = new PageSection();
                pageSection.setSectionName(currSection.getString("anchor"));
                pageSection.setIndexforQuery(currSection.getInt("index"));
                pageSection.setSectionNumber(currSection.getString("number"));
                pageSection.setPageTitle(currSection.getString("fromtitle"));
                addToSectionsList(pageSections, pageSection);
            }
        }catch (JSONException e)
        {
            Logger.logException(e);
        }
    return pageSections;
    }
    public static PageSection parseSectionContentIntoPageSection(JSONObject jsonObject, PageSection parseToHere)
    {
        try
        {
            parseToHere.setSecrionContentInHtml(getContentString(jsonObject));
        } catch (JSONException e)
        {
            Logger.logException(e);
        }
        return parseToHere;
    }
    private static void addToSectionsList (List<PageSection> pageSections, PageSection pageSection)
    {
        if (pageSection == null)
            return;
        if (pageSections.isEmpty())
        {
            pageSections.add(pageSection);
            return;
        }
        String sectionNumber = pageSection.getSectionNumber();
        if (!sectionNumber.contains("."))
        {
            pageSections.add(pageSection);
            return;
        }
        else
        {
            String [] subsections = sectionNumber.split("\\.");
            if (subsections.length == 2)
            {
                //mean we got only one point like: "1.2"
                for (PageSection tmpSection : pageSections)
                {
                    if (tmpSection.getSectionNumber().equals(subsections[0]))
                        tmpSection.appendSubSection(pageSection);
                }
            }
            else
            {
                // we got 2 points
                for (PageSection tmpSection : pageSections)
                {
                    if (pageSection.getSectionName().equals(subsections[0]))
                    {
                        for (PageSection subSubSection : tmpSection.getSubSections())
                        {
                            if (subSubSection.getSectionNumber().equals(subsections[1]))
                                subSubSection.appendSubSection(pageSection);
                        }
                    }
                }
            }

        }

    }
    public static HashMap<String, String> parseJsonToHashMap(JSONObject json)
    {
        HashMap<String, String> valuesToReturn = new HashMap<String, String>();
        if (json == null)
            return null;
        try
        {
            String jsonExtractedString = getContentString(json);
            valuesToReturn = extractHashMapFromString(jsonExtractedString);

        } catch (JSONException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return valuesToReturn;
    }

    private static String getContentString(JSONObject json) throws JSONException
    {
        JSONArray rvisions = getJsonArrayFromWikiResponse(json);

        JSONObject rev0 = (JSONObject) rvisions.get(0);
        String value = rev0.getString("*");
        Log.i(JsonParserUtil.class.getSimpleName(),"Finished extracting the content from JSON, content is: " + value);

        return value;
    }

    private static JSONArray getJsonArrayFromWikiResponse(JSONObject json) throws JSONException {
        JSONObject query = json.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");
        Iterator<?> keys = pages.keys();
        String key = (String) keys.next();
        JSONObject pageId = pages.getJSONObject(key);
        return pageId.getJSONArray("revisions");
    }

    private static HashMap<String, String> extractHashMapFromString(String input)
    {
        HashMap<String, String> mapToReturn = new HashMap<String, String>();

        // remove tue prefix string
        int index = input.indexOf("{{Infobox");
        input = input.substring(index);
        input = input.replaceAll("\\|","");
        String[] tokens = input.split("\\n");
        index =0;
        for (int i=0; i < tokens.length; i++ )
        {
            index = tokens[i].indexOf("=");
            if (index > -1)
            {
                String key = tokens[i].substring(0,index -1);
                key = key.trim();
                String value = tokens[i].substring(index +1);
                mapToReturn.put(key, value);
            }
            index =0;
        }
        return mapToReturn;
    }

  /*  public static void  parseDBPediaJson(JSONObject json)
    {
        Iterator<?> keys = json.keys();
        while (keys.hasNext())
        {

        }

    }*/

    public static void parseDbpediaJson (CityInfo.CityInfoBuilder builder, JSONObject json)
    {
        if (builder == null || json == null)
            return;
        try
        {
            Iterator<?> keys = json.keys();
            // get the resource json Object
            JSONObject resourceJson = json.getJSONObject((String)keys.next());
            builder.cityName(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_NAME));
            builder.utcOffset(parseInt(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_UTC_OFFSET)));
            builder.establishedDate(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_ESTABLISHED_DATE));
            builder.countryName(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_COUNTRY));
            builder.cityWebSite(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_WEB_SITE_URL));
            builder.geoLocation(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_LAT),getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_LANG));
            builder.imageFlagName(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_IMAGE_FLAG_NAME));
            builder.imageMapName(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_IMAGE_MAP_NAME));
            builder.imageSealName(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_IMAGE_SEAL_NAME));
            builder.imageSky(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_IMAGE_SKY_LINE));
            builder.wikiOrigPageUrl(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_ORIGINAL_WIKI_PAGE_URL));
            builder.postalCode(parseInt(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_POSTAL_CODE)));
            builder.totalPopulation(parseLong(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_TOTAL_POPULATION)));
            builder.wikipageId(parseInt(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_WIKI_ID)));
            builder.wikiRevisionId(parseInt(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_WIKI_REVISION_ID)));
            builder.nickName(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_NICK_NAME));
            builder.region(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_COORDINATES_REGION));
            builder.waterAreaPercentage(parseDouble(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_WATER_AREA_PERCENTAGE)));
            builder.numberofRainDaysAyear(parseDouble(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_NUMBER_OF_RAIN_DAYS_YEAR)));
            builder.yearMinTemp(parseDouble(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_YEAR_MIN_TEMP)));
            builder.yearMaxTemp(parseDouble(getFirstValueFromJsonArray(resourceJson, WikiConsts.CITY_YEAR_MAX_TEMP)));
            builder.cityGeneralInfo((getOnlyEnglishDataFromJsonArray(resourceJson, WikiConsts.CITY_GENERAL_INFO)));

            //external Links
            Collection<String> externalLinks = getAllValuesFromJsonArray(resourceJson, WikiConsts.CITY_WIKI_EXTERNAL_LINKS);
            for (String urlString : externalLinks)
            {
                builder.addExternalLink(new URL(urlString));
            }



        }catch (Exception e)
        {
            Logger.logException(e);
            return;
        }

    }
    private static String getOnlyEnglishDataFromJsonArray(JSONObject rootJson, String key)
    {
        try
        {
            JSONArray jsonArray = rootJson.getJSONArray(key);
            JSONObject currJson;
            for (int i=0; i < jsonArray.length() ; i++)
            {
                currJson = (JSONObject) jsonArray.get(i);
                if ("en".equals(currJson.getString("lang")))
                {
                    //this is the english object, return the value
                    return currJson.getString("value");
                }
            }
            return null;
        }catch (JSONException e)
        {
            Logger.logError("Error Occered while trying to parse: " + key);
            Logger.logException(e);
            return null;
        }
    }
    private static String getFirstValueFromJsonArray(JSONObject rootJson, String key)
    {
       try
       {
           JSONArray jsonArray = rootJson.getJSONArray(key);
           JSONObject firstJsonObject = (JSONObject)jsonArray.get(0);
           return firstJsonObject.getString("value");
       } catch (JSONException e)
       {
           Logger.logError("Error Occered while trying to parse: " + key);
           Logger.logException(e);
           return null;
       }
    }
    private static Collection<String> getAllValuesFromJsonArray (JSONObject rootJson, String key)
    {
        try
        {
            JSONArray jsonArray = rootJson.getJSONArray(key);
            ArrayList<String> valuesList = new ArrayList<String>();
            JSONObject currJson;
            for (int i=0; i < jsonArray.length() ; i++)
            {
                currJson = (JSONObject) jsonArray.get(i);
                valuesList.add(currJson.getString("value"));
            }
            return valuesList;
        }
        catch (JSONException e)
        {
            Logger.logError("Error Occered while trying to parse: " + key);
            Logger.logException(e);
            return null;
        }
    }
    private static int parseInt(String string)
    {
        if (string == null)
            return 0;
        string = string.replace("?","-");
        return Integer.parseInt(string);
    }
    private static long parseLong(String string)
    {
        if (string == null)
            return(long) 0;
        string = string.replace("?","-");
        return Long.parseLong(string);
    }
    private static double parseDouble(String string)
    {
        if (string == null)
            return 0;
        string = string.replace("?","-");
        return Double.parseDouble(string);
    }

}


