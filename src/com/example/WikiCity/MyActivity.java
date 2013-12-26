package com.example.WikiCity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import il.ac.bl.JsonParserUtil;
import il.ac.services.IQuery;
import il.ac.services.QueryWikipedia;
import il.ac.shenkar.common.DialogHelper;
import il.ac.shenkar.common.URLWithCallback;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MyActivity extends Activity implements IQuery
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        QueryWikipedia wikipedia = new QueryWikipedia();
        try
        {
            DialogHelper.showProgressDialog("fatching data from the web", this);
            wikipedia.execute(new URLWithCallback(this, new URL("http://en.wikipedia.org//w/api.php?action=query&prop=revisions&format=json&rvprop=content&rvsection=0&titles=Las%20Vegas")));
        } catch (MalformedURLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    @Override
    public void onQueryFinished(JSONObject returnedJson, Exception e)
    {
        TextView textView = (TextView) findViewById(R.id.justSomeText);
        if (e == null)
            textView.setText(returnedJson.toString());
        else
            textView.setText(e.getMessage());

        JsonParserUtil.parseJson(returnedJson);
        DialogHelper.closeProggresDialog();
    }
}
