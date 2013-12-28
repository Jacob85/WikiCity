package il.ac.services;

import android.os.AsyncTask;
import android.util.Log;
import il.ac.exceptions.QueryException;
import il.ac.shenkar.common.URLWithCallback;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/26/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryWikipediaJson extends AsyncTask <URLWithCallback, Integer, JSONObject>
{
    private IWikiQuery callback;
    private QueryException exception;
    @Override
    protected JSONObject doInBackground(URLWithCallback... params)
    {
        if (params[0] == null)
        {
            exception = new QueryException("Passed null URLWithCallback parameter");
            Log.i(QueryWikipediaJson.class.getSimpleName(), exception.getMessage());
            return null;
        }
        //set the callback to be the callback we received
        callback = params[0].getCallback();
        if (callback == null)
        {
            exception = new QueryException("Passed null callback parameter");
            Log.i(QueryWikipediaJson.class.getSimpleName(), exception.getMessage());
            return null;
        }

        String jsonString = null;
        JSONObject jsonToReturn  = null;
        try
        {
            jsonString = getFromWeb(params[0].getUrl());
            if (jsonString == null || jsonString.isEmpty())
                return null;
            jsonToReturn = new JSONObject(jsonString);
        } catch (IOException e)
        {
            exception = new QueryException("Error in Querying Wikipedia");
            Log.i(QueryWikipediaJson.class.getSimpleName(), exception.getMessage());

        } catch (JSONException e)
        {
            exception = new QueryException("Error in Creating JSON Object");
            Log.i(QueryWikipediaJson.class.getSimpleName(), exception.getMessage());
        }
        return jsonToReturn;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject)
    {
        super.onPostExecute(jsonObject);
        //return the JSON With callback
        callback.onQueryFinished(jsonObject, exception);
    }

    private String getFromWeb(URL url)  throws IOException
    {
            //open connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // Fetching the string
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader inReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inReader);
            StringBuilder responseBuilder = new StringBuilder();
            for (String line = bufferedReader.readLine();line!=null; line = bufferedReader.readLine())
            {
                responseBuilder.append(line);
            }

            urlConnection.disconnect();
            return responseBuilder.toString();
    }
}
