package il.ac.shenkar.common;

import il.ac.services.IQuery;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/26/13
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class URLWithCallback
{
    private URL url;
    private IQuery callback;

    public URLWithCallback(IQuery callback, URL url)
    {
        this.callback = callback;
        this.url = url;
    }

    public URLWithCallback(){}

    public URL getUrl()
    {
        return url;
    }

    public void setUrl(URL url)
    {
        this.url = url;
    }

    public IQuery getCallback()
    {
        return callback;
    }

    public void setCallback(IQuery callback)
    {
        this.callback = callback;
    }
}
