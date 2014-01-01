package il.ac.bl;

import android.util.Log;
import il.ac.exceptions.QueryException;
import il.ac.services.IWikiQuery;
import il.ac.services.QueryPurpose;
import il.ac.services.QueryWikipediaJson;
import il.ac.services.QueryWikipediaCallback;
import il.ac.shenkar.common.URLWithCallback;
import org.json.JSONObject;

import java.net.URL;

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
    private QueryWikipediaJson queryWikipediaJson;

    private DataAccessObject ()
    {
       queryWikipediaJson = new QueryWikipediaJson();
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
            queryWikipediaJson.execute(new URLWithCallback(new QueryWikipediaCallback()
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
    public void queryImageFromCity(URL urlToQuery, QueryWikipediaCallback<JSONObject> callback)
    {
        queryWikipediaJson.execute(new URLWithCallback(callback, urlToQuery));

    }


}
