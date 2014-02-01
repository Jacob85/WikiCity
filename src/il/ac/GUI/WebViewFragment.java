package il.ac.GUI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import il.ac.shenker.wiki.PageSection;

/**
 * Created by Amsalem on 01/02/14.
 */
public class WebViewFragment extends DialogFragment
{
    Context context;
    PageSection pageSection;
    WebView webView;

    public WebViewFragment(Context context, PageSection pageSection)
    {
        this.context = context;
        this.pageSection = pageSection;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(pageSection.getSectionName());

        webView = new WebView(context);
        //webView.loadData(pageSection.getSecrionContentInHtml(), "text/html", "UTF-8");
        webView.loadDataWithBaseURL("http://en.wikipedia.org/", pageSection.getSecrionContentInHtml(), "text/html", "UTF-8", null);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        builder.setView(webView);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
