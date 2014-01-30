package il.ac.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;
import android.os.Process;
import android.widget.ImageView;
import il.ac.shenkar.common.Logger;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Amsalem on 28/01/14.
 */
public class DownkoadImageRunnable implements Runnable
{
    ImageView bmImage;
    URL url;

    public DownkoadImageRunnable(ImageView bmImage, URL url)
    {
        this.bmImage = bmImage;
        this.url = url;
    }

    @Override
    public void run()
    {
        // set the priority to background
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        if (url == null)
        {
            Logger.logError("Error, Image URL = NULL! from: DownkoadImageRunnable");
            return;
        }
        Bitmap mIcon11 = null;
        try
        {
            InputStream in = url.openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
            return;
        } catch (Exception e)
        {
            Logger.logException(e);
            e.printStackTrace();
        }
        return;
    }

}
