package il.ac.shenker.wiki;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 1/1/14
 * Time: 8:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class WikiImageInfo
{
    private URL imageUrl;
    private String imageTitle;
    private ImageType imageType;

    private  WikiImageInfo(ImageInfoBuilder builder)
    {
        this.imageTitle = builder.imageTitle;
        this.imageType = builder.imageType;
        this.imageUrl = builder.imageUrl;
    }

    public URL getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getImageTitle()
    {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle)
    {
        this.imageTitle = imageTitle;
    }

    public ImageType getImageType()
    {
        return imageType;
    }

    public void setImageType(ImageType imageType)
    {
        this.imageType = imageType;
    }

    public static class ImageInfoBuilder
    {
        private URL imageUrl;
        private String imageTitle;
        private ImageType imageType;

        public ImageInfoBuilder(){};
        public ImageInfoBuilder Url(String url)
        {
            try
            {
                this.imageUrl = new URL(url);
            } catch (MalformedURLException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return this;
        }
        public ImageInfoBuilder Title(String imageTitle)
        {
            this.imageTitle = imageTitle;
            return this;
        }
        public ImageInfoBuilder Type (ImageType imageType)
        {
            this.imageType = imageType;
            return this;
        }
        public WikiImageInfo build ()
        {
            return new WikiImageInfo(this);
        }

    }
    public enum  ImageType
    {
        FLAG(true),
        SEAL(true),
        EXTRA(false);

        private boolean isMandatory;
        ImageType(boolean isMandatory)
        {
            this.isMandatory = isMandatory;
        }

        public boolean isMandatory()
        {
            return isMandatory;
        }
    }
}

