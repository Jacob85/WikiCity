package il.ac.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import il.ac.shenkar.common.Logger;

import java.io.InputStream;
import java.net.URL;

/**
 *
 */
public class DownloadImageTask extends AsyncTask<URL, Void, Bitmap> {
    ImageView bmImage;

    /**
     *  create Object from this class, send it an object to populate and a URL to Query the image from and the Task wil load the image to the Image View once it done;
     * @param bmImage - The Object to populate
     */
    public DownloadImageTask(ImageView bmImage)
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
        } catch (Exception e)
        {
            Logger.logException(e);
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result)
    {
        if (result != null)
            bmImage.setImageBitmap(result);
    }
}

