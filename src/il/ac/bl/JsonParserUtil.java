package il.ac.bl;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

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

    public static HashMap<String, String> parseJson(JSONObject json)
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
        JSONObject query = json.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");
        Iterator<?> keys = pages.keys();
        String key = (String) keys.next();
        JSONObject pageId = pages.getJSONObject(key);
        JSONArray rvisions = pageId.getJSONArray("revisions");

        JSONObject rev0 = (JSONObject) rvisions.get(0);
        String value = rev0.getString("*");
        Log.i(JsonParserUtil.class.getSimpleName(),"Finished extracting the content from JSON, content is: " + value);

        return value;
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
                String value = tokens[i].substring(index +1);
                mapToReturn.put(key, value);
            }
            index =0;
        }




        return mapToReturn;
    }

}


