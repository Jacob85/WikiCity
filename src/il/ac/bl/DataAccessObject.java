package il.ac.bl;

import android.util.Log;
import il.ac.exceptions.QueryException;
import il.ac.services.IWikiQuery;
import il.ac.services.QueryJsonFormWeb;
import il.ac.services.QueryWikipediaCallback;
import il.ac.shenkar.common.CityInfo;
import il.ac.shenkar.common.Logger;
import il.ac.shenkar.common.URLWithCallback;
import il.ac.shenker.wiki.PageSection;
import il.ac.shenker.wiki.WikiImageInfo;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/28/13
 * Time: 7:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataAccessObject implements IWikiQuery
{

    private static DataAccessObject instance;
    private QueryJsonFormWeb queryJsonFormWeb;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private DataAccessObject ()
    {
       queryJsonFormWeb = new QueryJsonFormWeb();
    }

    public static DataAccessObject getInstance()
    {
        if (instance == null)
            instance = new DataAccessObject();
        return instance;
    }

    @Override
    public void queryCityJson(URL urlToQuery, final QueryWikipediaCallback<JSONObject> callback) throws QueryException
    {
            //todo yaki - refactor, pass thw received callback to the query object instead of creating new one
            queryJsonFormWeb.execute(new URLWithCallback(new QueryWikipediaCallback()
            {
                @Override
                public void done(Object returnedObject, Exception e)
                {
                    // The returned JSON from the web will return to here, and from here we will return ot to the Main Activity by using the callback we received from it
                    if (returnedObject != null && e == null)
                        callback.done((JSONObject)returnedObject, e);
                    else if (e != null)
                    {
                        Log.e(this.getClass().getSimpleName(), e.getMessage());
                        callback.done((JSONObject)returnedObject, e);
                    }

                }
            } , urlToQuery));

    }

    @Override
    public void queryImageJsonFromCity(URL urlToQuery, QueryWikipediaCallback<JSONObject> callback)
    {
        queryJsonFormWeb.execute(new URLWithCallback(callback, urlToQuery));

    }

    @Override
    public void getCityInfo(String cityName, final QueryWikipediaCallback<CityInfo> callback)
    {
        atomicInteger = new AtomicInteger(0);
        QueryJsonFormWeb queryJsonFormWeb1 = new QueryJsonFormWeb();
        final CityInfo.CityInfoBuilder cityInfoBuilder = new CityInfo.CityInfoBuilder();
        getCityWikipediaSectionsSynctonized(cityName, cityInfoBuilder);
        queryJsonFormWeb1.execute(new URLWithCallback(new QueryWikipediaCallback()
        {
        @Override
        public void done(Object returnedObject, Exception e)
        {
            if (returnedObject == null && e != null)
                callback.done(null, e);

            Logger.logInfo("getCityInfo done : " + System.currentTimeMillis());
            Logger.logInfo("getCityInfo: Atomic int = " + atomicInteger);

            JsonParserUtil.parseDbpediaJson(cityInfoBuilder, (JSONObject)returnedObject);
            if (atomicInteger.incrementAndGet() == 1)
            {
                while (atomicInteger.get() == 1)
                {

                    try {
                        Thread.sleep(300);
                        Logger.logInfo("getCityInfo: sleeping...");
                    } catch (InterruptedException e1) {
                        Logger.logException(e);
                    }
                }

                callback.done(cityInfoBuilder.build(), null);
            }
            callback.done(cityInfoBuilder.build(), null);
        }
        }, QueryUrlGenerator.generateDbpediaQueryUrlForCity(cityName)));



    }
    private void  getCityWikipediaSectionsSynctonized(String cityName, final CityInfo.CityInfoBuilder builder)
    {
        if (cityName == null || cityName.isEmpty())
            return;

        QueryJsonFormWeb queryJsonFormWeb1 = new QueryJsonFormWeb();
        URL url = QueryUrlGenerator.generateSectionsQuery(cityName);
        queryJsonFormWeb1.execute(new URLWithCallback(new QueryWikipediaCallback() {
            @Override
            public void done(Object returnedObject, Exception e)
            {
                if (returnedObject != null && e == null)
                {
                    Logger.logInfo("getCityWikipediaSectionsSynctonized done : " + System.currentTimeMillis());
                    Logger.logInfo("getCityWikipediaSectionsSynctonized: Atomic int = " + atomicInteger);
                    List<PageSection> sections = JsonParserUtil.parseWikiPageSections((JSONObject) returnedObject);
                    builder.PageSections(sections);
                }
                atomicInteger.incrementAndGet();
                Logger.logInfo("getCityWikipediaSectionsSynctonized: increment atomic integer, Atomic int = " + atomicInteger);
                return;

            }
        }, url));


    }
    @Override
    public void getCityWikipediaSections(String cityName, final QueryWikipediaCallback<List<PageSection>> callback )
    {
        if (cityName == null || cityName.isEmpty())
        {
            callback.done(null, new QueryException("city name is null or empty"));
            return;
        }

        QueryJsonFormWeb queryJsonFormWeb1 = new QueryJsonFormWeb();
        URL url = QueryUrlGenerator.generateSectionsQuery(cityName);
        queryJsonFormWeb1.execute(new URLWithCallback(new QueryWikipediaCallback() {
            @Override
            public void done(Object returnedObject, Exception e)
            {
                if (returnedObject != null && e == null)
                {
                    List<PageSection> sections = JsonParserUtil.parseWikiPageSections((JSONObject) returnedObject);
                    callback.done(sections, e);  // e == null
                }
                else
                    callback.done(null,e);
            }
        }, url));


    }

    @Override
    public void getWikiSectionInfo(final PageSection pageSection, final QueryWikipediaCallback<PageSection> callback)
    {
        if (pageSection == null)
        {
            callback.done(null, new QueryException("Page Section is null"));
            return;
        }
        final URL url = QueryUrlGenerator.generatePageSectionContent(pageSection);
        QueryJsonFormWeb jsonFormWeb = new QueryJsonFormWeb();
        jsonFormWeb.execute(new URLWithCallback(new QueryWikipediaCallback()
        {
            @Override
            public void done(Object returnedObject, Exception e)
            {
                if (returnedObject == null && e != null)
                {
                    callback.done(null, e);
                    return;
                }
                // parse the returned Json
                JsonParserUtil.parseSectionContentIntoPageSection((JSONObject) returnedObject, pageSection);
                callback.done(pageSection, null);
            }
        }, url));


    }

    /**
     * This method constract a query url for the images name it received as a parameter
     * The response JSON is then being parsed by the {@link il.ac.bl.JsonParserUtil} to {@link il.ac.shenker.wiki.WikiImageInfo} objects
     * Once done, the callback is being called that returns an list of {@link il.ac.shenker.wiki.WikiImageInfo} objects
     * @param callback The callback is being called once the method is done, the calling object should implement {@link il.ac.services.QueryWikipediaCallback}
     * @param imagesNames The names of the images to query from wikipedia
     */
    @Override
    public void getImagesUrl(final QueryWikipediaCallback<List<WikiImageInfo>> callback, String... imagesNames)
    {
        if (imagesNames.length > 0)
        {
            URL multipleImagesUrl = QueryUrlGenerator.generateWikipediaImageQuery(imagesNames);
            QueryJsonFormWeb jsonFormWeb = new QueryJsonFormWeb();
            jsonFormWeb.execute(new URLWithCallback(new QueryWikipediaCallback()
            {
                @Override
                public void done(Object returnedObject, Exception e)
                {
                    if (returnedObject == null && e != null)
                        callback.done(null, e);
                    try
                    {
                        ArrayList<WikiImageInfo> infos = (ArrayList<WikiImageInfo>) JsonParserUtil.parseJsonToImagesList((JSONObject) returnedObject);
                        callback.done(infos, null);   // return List of the Images URL
                    } catch (JSONException e1)
                    {
                        Logger.logException(e1);
                        callback.done(null, e1);
                    }
                }
            }, multipleImagesUrl));

        }
        else
            callback.done(null, new QueryException("Error in Queryng Images URL, No Names received..."));
    }



}
