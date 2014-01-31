package il.ac.shenkar.common;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by Amsalem on 31/01/14.
 */
public class GifWebView extends WebView
{
    public GifWebView(Context context, String path)
    {
        super(context);
        loadUrl(path);
    }
}
