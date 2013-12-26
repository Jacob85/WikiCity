package il.ac.services;

import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/26/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IQuery
{
    public void onQueryFinished(JSONObject returnedJson, Exception e);
}
