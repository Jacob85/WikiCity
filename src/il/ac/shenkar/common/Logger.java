package il.ac.shenkar.common;

import android.util.Log;

import java.security.PublicKey;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Amsalem on 11/01/14.
 */
public class Logger
{
    private static String TAG = "WikiCityLogger";
    private Logger(){}

    public static void  logInfo(String message)
    {
        Log.i(TAG,message);
    }
    public static void logError(String errorMessage)
    {
        Log.e(TAG, errorMessage);
    }
    public static void logException (Exception e)
    {
        logError(e.getMessage());
        logError(e.getLocalizedMessage());
        logError(e.getStackTrace().toString());
    }

    public static void logMap (Map <?, ?> map)
    {
        for (Map.Entry<?, ?> entry : map.entrySet())
        {
            logInfo(entry.getKey() + " " + entry.getValue());
        }

    }

}
