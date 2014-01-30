package il.ac.bl;

import il.ac.shenkar.common.Logger;
import il.ac.shenker.wiki.WikiConsts;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Amsalem on 16/01/14.
 */
public class QueryUrlGenerator
{
    public static String imageQueryendPoint = "https://en.wikipedia.org/w/api.php?action=query&prop=imageinfo&format=json&iiprop=url&titles=";
    public static String imageNamePrefix = "File%3A";
    public static String imageSeperator = "%20%7C%20";

    public static URL generateWikipediaImageQuery (String[] images)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(imageQueryendPoint);
        if (images == null)
            return null;
        boolean toAddSeperator = false;
        for (String imageName : images)
        {
            if (toAddSeperator)
            {
                builder.append(imageSeperator);
            }
            builder.append(imageNamePrefix);
            imageName = imageName.replaceAll(" ", WikiConsts.SPACE);
            builder.append(imageName);
            toAddSeperator = true;
        }
        URL url = null;
        try
        {
            url = new URL(builder.toString());
        } catch (MalformedURLException e)
        {
            Logger.logException(e);
        }
        return url;
    }
    public static URL generateDbpediaQueryUrlForCity (String cityName)
    {
        cityName = cityName.replaceAll(" ", WikiConsts.SPACE);
        URL url = null;
        try
        {
            url = new URL("http://studentportal-jscapps.rhcloud.com/WikiCity/DBPediaQuery.jsp?City=" + cityName);
        } catch (MalformedURLException e)
        {
            Logger.logException(e);
        }
        return url;
    }

    public static URL generateSectionsQuery(String cityName)
    {
        cityName = cityName.replaceAll(" ", WikiConsts.SPACE);
        URL url = null;
        try
        {
            url = new URL("http://en.wikipedia.org/w/api.php?action=parse&format=json&prop=sections&page=" + cityName);
        } catch (MalformedURLException e)
        {
            Logger.logException(e);
        }
        return url;
    }
}
