package il.ac.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import il.ac.shenkar.common.Logger;

import java.io.InputStream;
import java.net.URL;

/**
 *
 */
public class DownloadImageAsyncTask extends AsyncTask<URL, Void, Bitmap> {
    ImageView bmImage;

    /**
     * create Object from this class, send it an object to populate and a URL to Query the image from and the Task wil load the image to the Image View once it done;
     * @param bmImage - The Object to populate
     */
    public DownloadImageAsyncTask(ImageView bmImage)
    {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(URL... urls)
    {
        if (urls[0] == null)
        {
            Logger.logError("Error, Image URL = NULL! from: " + this.getClass().getSimpleName());
            return null;
        }
        Bitmap mIcon11 = null;
        try
        {
            InputStream in = urls[0].openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
            return mIcon11;
        } catch (Exception e)
        {
            Logger.logException(e);
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Bitmap result)
    {
        if (result != null)
            bmImage.setImageBitmap(result);

    }
}

