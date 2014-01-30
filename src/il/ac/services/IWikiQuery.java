package il.ac.services;

import il.ac.exceptions.QueryException;
import il.ac.shenkar.common.CityInfo;
import il.ac.shenker.wiki.PageSection;
import il.ac.shenker.wiki.WikiImageInfo;
import org.json.JSONObject;

import java.net.URL;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/26/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IWikiQuery
{
    public void queryCityJson(URL urlToQuery, QueryWikipediaCallback<JSONObject> callback) throws QueryException;
    public void queryImageJsonFromCity(URL urlToQuery, QueryWikipediaCallback<JSONObject> callback);
    public void getCityInfo(String cityName, QueryWikipediaCallback<CityInfo> callback);
    public void getImagesUrl(QueryWikipediaCallback<List <WikiImageInfo>> callback, String... imagesNames);
    public void getCityWikipediaSections(String cityName, QueryWikipediaCallback<List<PageSection>> callback);

}
