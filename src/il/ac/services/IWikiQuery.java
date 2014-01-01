package il.ac.services;

import il.ac.exceptions.QueryException;
import org.json.JSONObject;

import java.net.URL;

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
    public void queryImageFromCity(URL urlToQuery, QueryWikipediaCallback<JSONObject> callback);
}
