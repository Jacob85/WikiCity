package il.ac.services;


import android.os.Handler;
import android.os.Looper;

/**
 * Created by Amsalem on 28/01/14.
 */
public class ThreadPoolManager
{
    private static ThreadPoolManager instance = null;
    private android.os.Handler handler = new Handler(Looper.getMainLooper())
    {

    };


    private ThreadPoolManager(){}

    public static ThreadPoolManager getInstance ()
    {
        if (instance == null)
            instance = new ThreadPoolManager();
        return instance;
    }


}
