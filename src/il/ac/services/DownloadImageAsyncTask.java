package il.ac.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import il.ac.shenkar.common.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
public class DownloadImageAsyncTask extends AsyncTask<URL, Void, Bitmap> {
    ImageView bmImage;
    boolean isSVG;

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
        if (urls[0].toString().endsWith(".svg"))
            isSVG = true;

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
       if (isSVG)
       {
           //bmImage.setBackground(svgToReturn.createPictureDrawable());
           return;
       }
       else if (result != null)
       {
           bmImage.setImageBitmap(result);
           //bmImage.setBackground(new BitmapDrawable(result));
       }
        else
           Logger.logError("image is null");
    }
}

